package com.betta.eng.service.impl;

import com.betta.common.utils.StringUtils;
import com.betta.eng.domain.EngArticle;
import com.betta.eng.domain.EngSentence;
import com.betta.eng.mapper.EngArticleMapper;
import com.betta.eng.service.IEngArticleService;
import com.betta.eng.service.IEngArticleWordRelService;
import com.betta.eng.service.IEngSentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 英语文章Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-31
 */
@Service
public class EngArticleServiceImpl implements IEngArticleService {
    @Autowired
    private EngArticleMapper engArticleMapper;

    @Autowired
    private IEngSentenceService sentenceService;

    @Autowired
    private IEngArticleWordRelService articleWordRel;

    /**
     * 查询英语文章
     *
     * @param id 英语文章主键
     * @return 英语文章
     */
    @Override
    public EngArticle selectEngArticleById(Long id) {
        return engArticleMapper.selectEngArticleById(id);
    }

    /**
     * 查询英语文章列表
     *
     * @param engArticle 英语文章
     * @return 英语文章
     */
    @Override
    public List<EngArticle> selectEngArticleList(EngArticle engArticle) {
        return engArticleMapper.selectEngArticleList(engArticle);
    }

    /**
     * 新增英语文章
     *
     * @param engArticle 英语文章
     * @return 结果
     */
    @Transactional
    @Override
    public int insertEngArticle(EngArticle engArticle) {
        int rows = engArticleMapper.insertEngArticle(engArticle);
        return rows;
    }

    /**
     * 修改英语文章
     *
     * @param engArticle 英语文章
     * @return 结果
     */
    @Transactional
    @Override
    public int updateEngArticle(EngArticle engArticle) {
        return engArticleMapper.updateEngArticle(engArticle);
    }


    /**
     * 删除英语文章信息
     *
     * @param id 英语文章主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteEngArticleById(Long id) {
        //删除句子
        sentenceService.deleteByArticle(id);
        //删除关联
        articleWordRel.deleteByArticle(id);
        return engArticleMapper.deleteEngArticleById(id);
    }
}
