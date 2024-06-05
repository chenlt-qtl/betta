package com.betta.eng.service;

import com.betta.eng.domain.EngArticleGroup;

import java.util.List;

/**
 * 英语文章分组Service接口
 * 
 * @author ruoyi
 * @date 2024-05-30
 */
public interface IEngArticleGroupService 
{
    /**
     * 查询英语文章分组
     * 
     * @param id 英语文章分组主键
     * @return 英语文章分组
     */
    public EngArticleGroup selectEngArticleGroupById(Long id);

    /**
     * 查询英语文章分组列表
     * 
     * @param engArticleGroup 英语文章分组
     * @return 英语文章分组集合
     */
    public List<EngArticleGroup> selectEngArticleGroupList(EngArticleGroup engArticleGroup);

    /**
     * 新增英语文章分组
     * 
     * @param engArticleGroup 英语文章分组
     * @return 结果
     */
    public int insertEngArticleGroup(EngArticleGroup engArticleGroup);

    /**
     * 修改英语文章分组
     * 
     * @param engArticleGroup 英语文章分组
     * @return 结果
     */
    public int updateEngArticleGroup(EngArticleGroup engArticleGroup);

    /**
     * 批量删除英语文章分组
     * 
     * @param ids 需要删除的英语文章分组主键集合
     * @return 结果
     */
    public int deleteEngArticleGroupByIds(Long[] ids);

}
