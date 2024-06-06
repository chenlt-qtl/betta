package com.betta.note.service.impl;

import java.util.List;
import com.betta.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betta.note.mapper.ContentMapper;
import com.betta.note.domain.Content;
import com.betta.note.service.IContentService;

/**
 * 笔记内容Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-06
 */
@Service
public class ContentServiceImpl implements IContentService
{
    @Autowired
    private ContentMapper contentMapper;

    /**
     * 查询笔记内容
     * 
     * @param id 笔记内容主键
     * @return 笔记内容
     */
    @Override
    public Content selectcontentById(Long id)
    {
        return contentMapper.selectcontentById(id);
    }

    /**
     * 查询笔记内容列表
     * 
     * @param content 笔记内容
     * @return 笔记内容
     */
    @Override
    public List<Content> selectcontentList(Content content)
    {
        return contentMapper.selectcontentList(content);
    }

    /**
     * 新增笔记内容
     * 
     * @param content 笔记内容
     * @return 结果
     */
    @Override
    public int insertcontent(Content content)
    {
        content.setCreateTime(DateUtils.getNowDate());
        return contentMapper.insertcontent(content);
    }

    /**
     * 修改笔记内容
     * 
     * @param content 笔记内容
     * @return 结果
     */
    @Override
    public int updatecontent(Content content)
    {
        content.setUpdateTime(DateUtils.getNowDate());
        return contentMapper.updatecontent(content);
    }

    /**
     * 批量删除笔记内容
     * 
     * @param ids 需要删除的笔记内容主键
     * @return 结果
     */
    @Override
    public int deletecontentByIds(Long[] ids)
    {
        return contentMapper.deletecontentByIds(ids);
    }

    /**
     * 删除笔记内容信息
     * 
     * @param id 笔记内容主键
     * @return 结果
     */
    @Override
    public int deletecontentById(Long id)
    {
        return contentMapper.deletecontentById(id);
    }
}
