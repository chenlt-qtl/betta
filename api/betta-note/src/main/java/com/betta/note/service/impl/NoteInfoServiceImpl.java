package com.betta.note.service.impl;

import com.betta.common.annotation.CreateByScope;
import com.betta.common.core.domain.TreeSelect;
import com.betta.common.exception.ServiceException;
import com.betta.common.utils.DateUtils;
import com.betta.common.utils.TreeUtil;
import com.betta.note.domain.Content;
import com.betta.note.domain.NoteInfo;
import com.betta.note.domain.NoteInfoDelete;
import com.betta.note.mapper.NoteInfoMapper;
import com.betta.note.service.IContentService;
import com.betta.note.service.INoteInfoDeleteService;
import com.betta.note.service.INoteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 文件夹Service业务层处理
 *
 * @author ruoyi
 * @date 2024-06-06
 */
@Service
public class NoteInfoServiceImpl implements INoteInfoService {
    @Autowired
    private NoteInfoMapper noteInfoMapper;

    @Autowired
    private IContentService contentService;

    @Autowired
    private INoteInfoDeleteService noteInfoDeleteService;

    /**
     * 查询文件夹
     *
     * @param id 文件夹主键
     * @return 文件夹
     */
    @Override
    public NoteInfo selectNoteInfoById(Long id) {
        return noteInfoMapper.selectNoteInfoById(id);
    }

    /**
     * 查询文件夹列表
     *
     * @param noteInfo 文件夹
     * @return 文件夹
     */
    @Override
    public List<NoteInfo> selectNoteInfoList(NoteInfo noteInfo) {
        return noteInfoMapper.selectNoteInfoList(noteInfo);
    }

    /**
     * 新增文件夹
     *
     * @param noteInfo 文件夹
     * @return 结果
     */
    @Override
    public int insertNoteInfo(NoteInfo noteInfo) {
        noteInfo.setCreateTime(DateUtils.getNowDate());
        return noteInfoMapper.insertNoteInfo(noteInfo);
    }

    /**
     * 修改文件夹
     *
     * @param noteInfo 文件夹
     * @return 结果
     */
    @Override
    public int updateNoteInfo(NoteInfo noteInfo) {
        noteInfo.setUpdateTime(DateUtils.getNowDate());
        return noteInfoMapper.updateNoteInfo(noteInfo);
    }

    /**
     * 批量删除文件夹
     *
     * @param ids 需要删除的文件夹主键
     * @return 结果
     */
    @Override
    public int deleteNoteInfoByIds(Long[] ids) {
        for (Long id : ids) {

            NoteInfo noteInfo = noteInfoMapper.selectNoteInfoById(id);

            //文件夹要检查一下是否有子文件
            if (!noteInfo.getIsLeaf()) {
                //检查是否有子节点
                NoteInfo query = new NoteInfo();
                query.setParentId(id);
                List<NoteInfo> children = noteInfoMapper.selectNoteInfoList(query);
                if (!children.isEmpty()) {
                    throw new ServiceException("删除的文件夹有子文件，请先删除子文件");
                }
            }

            //保存到历史表
            NoteInfoDelete noteInfoDelete = new NoteInfoDelete(noteInfo);

            if (!Objects.isNull(noteInfo.getContentId())) {
                Content noteContent = contentService.selectcontentById(noteInfo.getContentId());
                noteInfoDelete.setText(noteContent.getText());
            }
            noteInfoDeleteService.insertNoteInfoDelete(noteInfoDelete);
        }

        return noteInfoMapper.deleteNoteInfoByIds(ids);
    }

    @Override
    @CreateByScope("")
    public List<TreeSelect> selectNoteTreeList(NoteInfo noteInfo) {
        List<TreeSelect> notes = noteInfoMapper.selectTreeSelect(noteInfo);
        return TreeUtil.wrapTreeDataToTreeList(notes, noteInfo.getParentId());
    }


}
