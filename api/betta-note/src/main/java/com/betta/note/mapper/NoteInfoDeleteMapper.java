package com.betta.note.mapper;

import java.util.List;
import com.betta.note.domain.NoteInfoDelete;

/**
 * 删除历史Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-06
 */
public interface NoteInfoDeleteMapper 
{
    /**
     * 查询删除历史
     * 
     * @param id 删除历史主键
     * @return 删除历史
     */
    public NoteInfoDelete selectNoteInfoDeleteById(Long id);

    /**
     * 查询删除历史列表
     * 
     * @param noteInfoDelete 删除历史
     * @return 删除历史集合
     */
    public List<NoteInfoDelete> selectNoteInfoDeleteList(NoteInfoDelete noteInfoDelete);

    /**
     * 新增删除历史
     * 
     * @param noteInfoDelete 删除历史
     * @return 结果
     */
    public int insertNoteInfoDelete(NoteInfoDelete noteInfoDelete);

    /**
     * 修改删除历史
     * 
     * @param noteInfoDelete 删除历史
     * @return 结果
     */
    public int updateNoteInfoDelete(NoteInfoDelete noteInfoDelete);

    /**
     * 删除删除历史
     * 
     * @param id 删除历史主键
     * @return 结果
     */
    public int deleteNoteInfoDeleteById(Long id);

    /**
     * 批量删除删除历史
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNoteInfoDeleteByIds(Long[] ids);
}
