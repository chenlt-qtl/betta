package com.betta.eng.service.impl;

import com.betta.common.utils.StringUtils;
import com.betta.eng.domain.EngArticle;
import com.betta.eng.domain.EngSentence;
import com.betta.eng.mapper.EngArticleMapper;
import com.betta.eng.service.IEngArticleService;
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
        insertEngSentence(engArticle);
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
     * 批量删除英语文章
     *
     * @param ids 需要删除的英语文章主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteEngArticleByIds(Long[] ids) {
        engArticleMapper.deleteEngSentenceByArticleIds(ids);
        return engArticleMapper.deleteEngArticleByIds(ids);
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
        engArticleMapper.deleteEngSentenceByArticleId(id);
        return engArticleMapper.deleteEngArticleById(id);
    }

    /**
     * 新增文章句子信息
     *
     * @param engArticle 英语文章对象
     */
    public void insertEngSentence(EngArticle engArticle) {
        List<EngSentence> engSentenceList = engArticle.getEngSentenceList();
        Long id = engArticle.getId();
        if (StringUtils.isNotNull(engSentenceList)) {
            List<EngSentence> list = new ArrayList<EngSentence>();
            for (EngSentence engSentence : engSentenceList) {
                engSentence.setArticleId(id);
                list.add(engSentence);
            }
            if (list.size() > 0) {
                engArticleMapper.batchEngSentence(list);
            }
        }
    }
}
