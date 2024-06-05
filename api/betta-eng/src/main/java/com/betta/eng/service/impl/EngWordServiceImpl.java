package com.betta.eng.service.impl;

import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;

import com.betta.common.annotation.CreateByScope;
import com.betta.common.utils.DateUtils;
import com.betta.common.utils.SecurityUtils;
import com.betta.eng.domain.*;
import com.betta.eng.service.*;
import com.betta.eng.utils.ParseIciba;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betta.eng.mapper.EngWordMapper;
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
    private IEngSentenceService sentenceService;

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
    @CreateByScope("")
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
        //查询单词
        List<EngWord> list = engWordMapper.selectEngWordByWordName(lowerCase);
        EngWord word = null;
        if (!list.isEmpty()) {
            word = list.get(0);//已存在数据库中

            //查询爱词霸例句
            EngIcibaSentence icibaSentence = new EngIcibaSentence();
            icibaSentence.setWordId(word.getId());
            List<EngIcibaSentence> icibaSentences = icibaSentenceService.selectEngIcibaSentenceList(icibaSentence);
            word.setIcibaSentenceList(icibaSentences);
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

        //查询自定义例句
        EngSentence sentence = new EngSentence();
        sentence.setCreateBy(SecurityUtils.getUsername());
        sentence.setContent(wordName);
        List<EngSentence> sentences = sentenceService.selectEngSentenceList(sentence);
        word.setSentenceList(sentences);

        //查询是否已关联
        EngArticleWordRel engArticleWordRel = new EngArticleWordRel();
        engArticleWordRel.setWordId(word.getId());
        engArticleWordRel.setCreateBy(SecurityUtils.getUsername());
        List<EngArticleWordRel> engArticleWordRels = articleWordRelService.selectEngArticleWordRelList(engArticleWordRel);
        if(!engArticleWordRels.isEmpty()) {
            word.setRelId(engArticleWordRels.get(0).getId());
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

    @Override
    public List<EngWord> selectNewList(EngWord engWord) {
        engWord.setCreateBy(SecurityUtils.getUsername());//当前用户的数据
        return engWordMapper.selectRelList(engWord);
    }
}
