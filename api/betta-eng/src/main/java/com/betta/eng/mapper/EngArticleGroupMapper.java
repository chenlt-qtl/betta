package com.betta.eng.mapper;

import com.betta.eng.domain.EngArticleGroup;

import java.util.List;

/**
 * 英语文章分组Mapper接口
 * 
 * @author ruoyi
 * @date 2024-05-30
 */
public interface EngArticleGroupMapper 
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
     * 删除英语文章分组
     * 
     * @param id 英语文章分组主键
     * @return 结果
     */
    public int deleteEngArticleGroupById(Long id);

    /**
     * 批量删除英语文章分组
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEngArticleGroupByIds(Long[] ids);
}
