package com.betta.eng.mapper;

import java.util.List;
import com.betta.eng.domain.EngArticle;
import com.betta.eng.domain.EngSentence;

/**
 * 英语文章Mapper接口
 * 
 * @author ruoyi
 * @date 2024-05-31
 */
public interface EngArticleMapper 
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
     * 删除英语文章
     * 
     * @param id 英语文章主键
     * @return 结果
     */
    public int deleteEngArticleById(Long id);

    /**
     * 批量删除英语文章
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEngArticleByIds(Long[] ids);

    /**
     * 批量删除文章句子
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEngSentenceByArticleIds(Long[] ids);
    
    /**
     * 批量新增文章句子
     * 
     * @param engSentenceList 文章句子列表
     * @return 结果
     */
    public int batchEngSentence(List<EngSentence> engSentenceList);
    

    /**
     * 通过英语文章主键删除文章句子信息
     * 
     * @param id 英语文章ID
     * @return 结果
     */
    public int deleteEngSentenceByArticleId(Long id);
}
