package com.betta.note.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.betta.common.core.domain.TreeSelect;
import com.betta.note.domain.NoteInfo;
import com.betta.note.domain.NoteVo;
import org.apache.ibatis.annotations.Param;

/**
 * 文件夹Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-06
 */
public interface NoteInfoMapper extends BaseMapper<NoteInfo>
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

    public List<NoteVo> selectNoteDetailList(NoteInfo noteInfo);

    public Long selectNoteCount(NoteInfo noteInfo);

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
     * 删除文件夹
     * 
     * @param id 文件夹主键
     * @return 结果
     */
    public int deleteNoteInfoById(Long id);

    /**
     * 批量删除文件夹
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNoteInfoByIds(Long[] ids);

    List<TreeSelect> selectTreeSelect(NoteInfo noteInfo);

    void updateParent(@Param("array") String[] ids, @Param("parentId") Long parentId);

    List<NoteInfo> selectNoteInfoByIds(String[] noteIds);

    List<NoteInfo> selectLast(Map param);
}
