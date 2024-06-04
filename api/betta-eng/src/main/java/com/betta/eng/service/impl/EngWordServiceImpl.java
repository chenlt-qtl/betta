package com.betta.eng.service.impl;

import java.util.List;
import java.util.Objects;

import com.betta.common.utils.DateUtils;
import com.betta.eng.domain.EngArticleWordRel;
import com.betta.eng.domain.EngIcibaSentence;
import com.betta.eng.service.IEngArticleWordRelService;
import com.betta.eng.service.IEngIcibaSentenceService;
import com.betta.eng.utils.ParseIciba;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betta.eng.mapper.EngWordMapper;
import com.betta.eng.domain.EngWord;
import com.betta.eng.service.IEngWordService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 单词Service业务层处理
 *
 * @author ruoyi
 * @date 2024-06-03
 */
@Service
public class EngWordServiceImpl implements IEngWordService {
    @Autowired
    private EngWordMapper engWordMapper;

    @Autowired
    private IEngArticleWordRelService articleWordRelService;

    @Autowired
    private IEngIcibaSentenceService icibaSentenceService;

    /**
     * 查询单词
     *
     * @param id 单词主键
     * @return 单词
     */
    @Override
    public EngWord selectEngWordById(Long id) {
        return engWordMapper.selectEngWordById(id);
    }

    /**
     * 查询单词列表
     *
     * @param engWord 单词
     * @return 单词
     */
    @Override
    public List<EngWord> selectEngWordList(EngWord engWord) {
        return engWordMapper.selectEngWordList(engWord);
    }

    @Override
    public List<EngWord> selectWordListByArticle(Long articleId) {
        return engWordMapper.selectWordListByArticleId(articleId);
    }

    /**
     * 新增单词
     *
     * @param engWord 单词
     * @return 结果
     */
    @Override
    public int insertEngWord(EngWord engWord) {
        engWord.setCreateTime(DateUtils.getNowDate());
        return engWordMapper.insertEngWord(engWord);
    }

    @Override
    public EngWord getWord(String wordName) {
        String lowerCase = wordName.toLowerCase();//转小写
        List<EngWord> list = engWordMapper.selectEngWordByWordName(lowerCase);
        EngWord word = null;
        if (!list.isEmpty()) {
            word = list.get(0);//已存在数据库中
        } else {//查API
            word = ParseIciba.getWordFromIciba(lowerCase);
            if (!Objects.isNull(word)) {
                insertEngWord(word);
                if (!Objects.isNull(word.getIcibaSentenceList())) {//例句
                    for (EngIcibaSentence icibaSentence : word.getIcibaSentenceList()) {
                        icibaSentence.setWordId(word.getId());
                        icibaSentenceService.insertEngIcibaSentence(icibaSentence);
                    }
                }
            }
        }
        return word;
    }

    @Override
    @Transactional
    public void addByArticle(List<String> wordNames, Long articleId) {
        for (String wordName : wordNames) {
            Long wordId = null;
            try {
                EngWord word = getWord(wordName);
                wordId = word.getId();

            } catch (Exception e) {
                e.printStackTrace();
            }
            if (wordId != null) {
                //与文章关联
                EngArticleWordRel articleWordRel = new EngArticleWordRel();
                articleWordRel.setArticleId(articleId);
                articleWordRel.setWordId(wordId);
                List<EngArticleWordRel> articleWordRelList = articleWordRelService.selectEngArticleWordRelList(articleWordRel);
                if (articleWordRelList.isEmpty()) {
                    articleWordRelService.insertEngArticleWordRel(articleWordRel);
                }
            }
        }
    }

    /**
     * 修改单词
     *
     * @param engWord 单词
     * @return 结果
     */
    @Override
    public int updateEngWord(EngWord engWord) {
        engWord.setUpdateTime(DateUtils.getNowDate());
        return engWordMapper.updateEngWord(engWord);
    }

    /**
     * 删除单词信息
     *
     * @param id 单词主键
     */
    @Override
    @Transactional
    public void deleteEngWordById(Long id) {
        icibaSentenceService.deleteByWordId(id);
        engWordMapper.deleteEngWordById(id);
    }
}
