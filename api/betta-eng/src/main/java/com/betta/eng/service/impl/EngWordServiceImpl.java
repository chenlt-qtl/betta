package com.betta.eng.service.impl;

import com.betta.common.annotation.CreateByScope;
import com.betta.common.exception.ServiceException;
import com.betta.common.utils.SecurityUtils;
import com.betta.common.utils.StringUtils;
import com.betta.eng.domain.*;
import com.betta.eng.mapper.EngWordMapper;
import com.betta.eng.service.*;
import com.betta.eng.utils.dict.DictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    private IEngUserScoreService userScoreService;

    @Autowired
    private IEngSentenceService sentenceService;

    @Autowired
    private IEngIcibaSentenceService icibaSentenceService;

    @Autowired
    private DictUtils dictUtils;

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
    public int addEngWord(EngWord engWord) {
        //先检查重复
        List<EngWord> engWords = engWordMapper.selectEngWordByWordName(engWord.getWordName());
        if (!Objects.isNull(engWords) && !engWords.isEmpty()) {
            throw new ServiceException("单词 " + engWord.getWordName() + " 已存在");
        }
        return engWordMapper.insertEngWord(engWord);
    }

    @Override
    public EngWordVo getWordVo(String wordName) {
        EngWordVo word = (EngWordVo) getWord(wordName);
        //查询爱词霸例句
        EngIcibaSentence icibaSentence = new EngIcibaSentence();
        icibaSentence.setWordId(word.getId());
        List<EngIcibaSentence> icibaSentences = icibaSentenceService.selectEngIcibaSentenceList(icibaSentence);
        word.setIcibaSentenceList(icibaSentences);

        //查询自定义例句
        List<EngSentence> sentences = sentenceService.selectByWordTop10(wordName);
        word.setSentenceList(sentences);

        //查询是否已关联
        EngArticleWordRel engArticleWordRel = new EngArticleWordRel();
        engArticleWordRel.setWordName(word.getWordName());
        engArticleWordRel.setCreateBy(SecurityUtils.getUsername());
        List<EngArticleWordRel> engArticleWordRels = articleWordRelService.selectEngArticleWordRelList(engArticleWordRel);
        if (!engArticleWordRels.isEmpty()) {
            word.setRelId(engArticleWordRels.get(0).getId());
        }
        return word;
    }

    @Override
    public EngWord getWord(String wordName) {
        String lowerCase = wordName.toLowerCase();//转小写
        //查询单词
        List<EngWord> list = engWordMapper.selectEngWordByWordName(lowerCase);
        EngWord word = null;
        boolean getFromApi = true;//是否从API查询数据
        if (!list.isEmpty()) {
            word = list.get(0);//已存在数据库中
            if (StringUtils.isNotBlank(word.getPhonetics()) && StringUtils.isNotBlank(word.getPhMp3())
                    && StringUtils.isNotBlank(word.getAcceptation())) {
                getFromApi = false;
            }
        }
        //查API
        if (getFromApi) {
            EngWordVo wordVo = dictUtils.getWord(lowerCase);
            word = wordVo;
            if (!Objects.isNull(wordVo)) {
                engWordMapper.insertEngWord(word);
                if (!Objects.isNull(wordVo.getIcibaSentenceList())) {//例句
                    for (EngIcibaSentence icibaSentence : wordVo.getIcibaSentenceList()) {
                        icibaSentence.setWordId(word.getId());
                        icibaSentenceService.insertEngIcibaSentence(icibaSentence);
                    }
                }
            }
        }
        return word;
    }

    /**
     * 更新文章的所有关联单词
     *
     * @param wordNames
     * @param articleId
     */
    @Override
    @Transactional
    public void updateByArticle(List<String> wordNames, Long articleId) {
        EngArticleWordRel articleWordRel = new EngArticleWordRel();
        articleWordRel.setArticleId(articleId);
        List<EngArticleWordRel> articleWordRelList = articleWordRelService.selectEngArticleWordRelList(articleWordRel);
        List<Long> keptIds = new ArrayList<>();

        for (String wordName : wordNames) {
            String wordNameLowerCase = wordName.toLowerCase();
            List<EngArticleWordRel> exists = articleWordRelList.stream().filter(engArticleWordRel -> StringUtils.endsWithIgnoreCase(engArticleWordRel.getWordName(), wordNameLowerCase)).collect(Collectors.toList());
            //如果未关联，增加关联
            if (exists.isEmpty()) {
                Long wordId = null;
                try {
                    EngWord word = getWord(wordNameLowerCase);
                    wordId = word.getId();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (wordId != null) {
                    articleWordRel = new EngArticleWordRel();
                    articleWordRel.setArticleId(articleId);
                    articleWordRel.setWordName(wordNameLowerCase);
                    articleWordRelService.insertEngArticleWordRel(articleWordRel);
                }
            } else {
                keptIds.add(exists.get(0).getId());
            }
        }

        //删除
        Long[] removeIds = articleWordRelList.stream()
                .filter(engArticleWordRel -> !keptIds.contains(engArticleWordRel.getId()))
                .map(e -> e.getId()).collect(Collectors.toList()).toArray(new Long[]{});
        articleWordRelService.deleteEngArticleWordRelByIds(removeIds);

    }

    /**
     * 修改单词
     *
     * @param engWord 单词
     * @return 结果
     */
    @Override
    public int updateEngWord(EngWord engWord) {
        EngWord db = getWord(engWord.getWordName());
        db.setPhonetics(engWord.getPhonetics());
        db.setPhMp3(engWord.getPhMp3());
        db.setAcceptation(engWord.getAcceptation());
        db.setExchange(engWord.getExchange());
        return engWordMapper.updateEngWord(db);
    }

    @Override
    public EngWord getWordFromApi(String wordName) {
        return dictUtils.getWord(wordName.toLowerCase());
    }

    /**
     * 删除单词信息
     *
     * @param ids 单词主键
     */
    @Override
    @Transactional
    public void deleteEngWordByIds(Long[] ids) {
        for (Long id : ids) {
            icibaSentenceService.deleteByWordId(id);
            engWordMapper.deleteEngWordById(id);
        }
    }

    @Override
    @CreateByScope("")
    public List<EngWord> selectNewList(EngWord engWord) {
        engWord.setCreateBy(SecurityUtils.getUsername());//当前用户的数据
        return engWordMapper.selectRelList(engWord);
    }

    /**
     * 增加文章对应的单词
     *
     * @param articleId
     * @param wordName
     */
    @Override
    public void addArticleWord(Long articleId, String wordName) {


        //先查询单词
        getWord(wordName);
        //先查询是否已关联
        EngArticleWordRel articleWordRel = new EngArticleWordRel();
        articleWordRel.setArticleId(articleId);
        articleWordRel.setWordName(wordName);
        List<EngArticleWordRel> articleWordRelList = articleWordRelService.selectEngArticleWordRelList(articleWordRel);
        if (articleWordRelList.isEmpty()) {
            //关联单词和文章
            articleWordRel = new EngArticleWordRel();
            articleWordRel.setArticleId(articleId);
            articleWordRel.setWordName(wordName);
            articleWordRelService.insertEngArticleWordRel(articleWordRel);
        } else {
            userScoreService.updateEngUserScore(wordName, -1);
        }
    }
}
