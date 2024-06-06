package com.betta.note.service;

import java.util.List;

import com.betta.common.core.domain.TreeSelect;
import com.betta.note.domain.NoteInfo;

/**
 * 文件夹Service接口
 * 
 * @author ruoyi
 * @date 2024-06-06
 */
public interface INoteInfoService 
{
    /**
     * 查询文件夹
     * 
     * @param id 文件夹主键
     * @return 文件夹
     */
    public NoteInfo selectNoteInfoById(Long id);

    /**
     * 查询文件夹列表
     * 
     * @param noteInfo 文件夹
     * @return 文件夹集合
     */
    public List<NoteInfo> selectNoteInfoList(NoteInfo noteInfo);

    /**
     * 新增文件夹
     * 
     * @param noteInfo 文件夹
     * @return 结果
     */
    public int insertNoteInfo(NoteInfo noteInfo);

    /**
     * 修改文件夹
     * 
     * @param noteInfo 文件夹
     * @return 结果
     */
    public int updateNoteInfo(NoteInfo noteInfo);

    /**
     * 批量删除文件夹
     * 
     * @param ids 需要删除的文件夹主键集合
     * @return 结果
     */
    public int deleteNoteInfoByIds(Long[] ids);


    List<TreeSelect> selectNoteTreeList(NoteInfo noteInfo);
}
