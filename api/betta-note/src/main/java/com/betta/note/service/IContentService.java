package com.betta.note.service;

import java.util.List;
import com.betta.note.domain.Content;

/**
 * 笔记内容Service接口
 * 
 * @author ruoyi
 * @date 2024-06-06
 */
public interface IContentService
{
    /**
     * 查询笔记内容
     * 
     * @param id 笔记内容主键
     * @return 笔记内容
     */
    public Content selectcontentById(Long id);

    /**
     * 查询笔记内容列表
     * 
     * @param content 笔记内容
     * @return 笔记内容集合
     */
    public List<Content> selectcontentList(Content content);

    /**
     * 新增笔记内容
     * 
     * @param content 笔记内容
     * @return 结果
     */
    public int insertcontent(Content content);

    /**
     * 修改笔记内容
     * 
     * @param content 笔记内容
     * @return 结果
     */
    public int updatecontent(Content content);

    /**
     * 批量删除笔记内容
     * 
     * @param ids 需要删除的笔记内容主键集合
     * @return 结果
     */
    public int deletecontentByIds(Long[] ids);

    /**
     * 删除笔记内容信息
     * 
     * @param id 笔记内容主键
     * @return 结果
     */
    public int deletecontentById(Long id);
}
