package com.betta.eng.service.impl;

import java.util.List;

import com.betta.eng.domain.EngArticleGroup;
import com.betta.eng.mapper.EngArticleGroupMapper;
import com.betta.eng.service.IEngArticleGroupService;
import com.betta.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 英语文章分组Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-05-30
 */
@Service
public class EngArticleGroupServiceImpl implements IEngArticleGroupService
{
    @Autowired
    private EngArticleGroupMapper engArticleGroupMapper;

    /**
     * 查询英语文章分组
     * 
     * @param id 英语文章分组主键
     * @return 英语文章分组
     */
    @Override
    public EngArticleGroup selectEngArticleGroupById(Long id)
    {
        return engArticleGroupMapper.selectEngArticleGroupById(id);
    }

    /**
     * 查询英语文章分组列表
     * 
     * @param engArticleGroup 英语文章分组
     * @return 英语文章分组
     */
    @Override
    public List<EngArticleGroup> selectEngArticleGroupList(EngArticleGroup engArticleGroup)
    {
        return engArticleGroupMapper.selectEngArticleGroupList(engArticleGroup);
    }

    /**
     * 新增英语文章分组
     * 
     * @param engArticleGroup 英语文章分组
     * @return 结果
     */
    @Override
    public int insertEngArticleGroup(EngArticleGroup engArticleGroup)
    {
        engArticleGroup.setCreateTime(DateUtils.getNowDate());
        return engArticleGroupMapper.insertEngArticleGroup(engArticleGroup);
    }

    /**
     * 修改英语文章分组
     * 
     * @param engArticleGroup 英语文章分组
     * @return 结果
     */
    @Override
    public int updateEngArticleGroup(EngArticleGroup engArticleGroup)
    {
        engArticleGroup.setUpdateTime(DateUtils.getNowDate());
        return engArticleGroupMapper.updateEngArticleGroup(engArticleGroup);
    }

    /**
     * 批量删除英语文章分组
     * 
     * @param ids 需要删除的英语文章分组主键
     * @return 结果
     */
    @Override
    public int deleteEngArticleGroupByIds(Long[] ids)
    {
        return engArticleGroupMapper.deleteEngArticleGroupByIds(ids);
    }

    /**
     * 删除英语文章分组信息
     * 
     * @param id 英语文章分组主键
     * @return 结果
     */
    @Override
    public int deleteEngArticleGroupById(Long id)
    {
        return engArticleGroupMapper.deleteEngArticleGroupById(id);
    }
}
