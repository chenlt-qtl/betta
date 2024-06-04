package com.betta.eng.service;

import java.util.List;
import com.betta.eng.domain.EngArticle;

/**
 * 英语文章Service接口
 * 
 * @author ruoyi
 * @date 2024-05-31
 */
public interface IEngArticleService 
{
    /**
     * 查询英语文章
     * 
     * @param id 英语文章主键
     * @return 英语文章
     */
    public EngArticle selectEngArticleById(Long id);

    /**
     * 查询英语文章列表
     * 
     * @param engArticle 英语文章
     * @return 英语文章集合
     */
    public List<EngArticle> selectEngArticleList(EngArticle engArticle);

    /**
     * 新增英语文章
     * 
     * @param engArticle 英语文章
     * @return 结果
     */
    public int insertEngArticle(EngArticle engArticle);

    /**
     * 修改英语文章
     * 
     * @param engArticle 英语文章
     * @return 结果
     */
    public int updateEngArticle(EngArticle engArticle);

    /**
     * 批量删除英语文章
     * 
     * @param ids 需要删除的英语文章主键集合
     * @return 结果
     */
    public int deleteEngArticleByIds(Long[] ids);

    /**
     * 删除英语文章信息
     * 
     * @param id 英语文章主键
     * @return 结果
     */
    public int deleteEngArticleById(Long id);

}
