package com.betta.note.service.impl;

import java.util.List;
import com.betta.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betta.note.mapper.NoteInfoDeleteMapper;
import com.betta.note.domain.NoteInfoDelete;
import com.betta.note.service.INoteInfoDeleteService;

/**
 * 删除历史Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-06
 */
@Service
public class NoteInfoDeleteServiceImpl implements INoteInfoDeleteService 
{
    @Autowired
    private NoteInfoDeleteMapper noteInfoDeleteMapper;

    /**
     * 查询删除历史
     * 
     * @param id 删除历史主键
     * @return 删除历史
     */
    @Override
    public NoteInfoDelete selectNoteInfoDeleteById(Long id)
    {
        return noteInfoDeleteMapper.selectNoteInfoDeleteById(id);
    }

    /**
     * 查询删除历史列表
     * 
     * @param noteInfoDelete 删除历史
     * @return 删除历史
     */
    @Override
    public List<NoteInfoDelete> selectNoteInfoDeleteList(NoteInfoDelete noteInfoDelete)
    {
        return noteInfoDeleteMapper.selectNoteInfoDeleteList(noteInfoDelete);
    }

    /**
     * 新增删除历史
     * 
     * @param noteInfoDelete 删除历史
     * @return 结果
     */
    @Override
    public int insertNoteInfoDelete(NoteInfoDelete noteInfoDelete)
    {
        noteInfoDelete.setCreateTime(DateUtils.getNowDate());
        return noteInfoDeleteMapper.insertNoteInfoDelete(noteInfoDelete);
    }

    /**
     * 修改删除历史
     * 
     * @param noteInfoDelete 删除历史
     * @return 结果
     */
    @Override
    public int updateNoteInfoDelete(NoteInfoDelete noteInfoDelete)
    {
        noteInfoDelete.setUpdateTime(DateUtils.getNowDate());
        return noteInfoDeleteMapper.updateNoteInfoDelete(noteInfoDelete);
    }

    /**
     * 批量删除删除历史
     * 
     * @param ids 需要删除的删除历史主键
     * @return 结果
     */
    @Override
    public int deleteNoteInfoDeleteByIds(Long[] ids)
    {
        return noteInfoDeleteMapper.deleteNoteInfoDeleteByIds(ids);
    }

    /**
     * 删除删除历史信息
     * 
     * @param id 删除历史主键
     * @return 结果
     */
    @Override
    public int deleteNoteInfoDeleteById(Long id)
    {
        return noteInfoDeleteMapper.deleteNoteInfoDeleteById(id);
    }
}
