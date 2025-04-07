package com.betta.note.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.betta.common.annotation.CreateByScope;
import com.betta.common.core.domain.TreeSelect;
import com.betta.common.exception.ApiException;
import com.betta.common.utils.DateUtils;
import com.betta.common.utils.SecurityUtils;
import com.betta.common.utils.TreeUtil;
import com.betta.note.domain.Content;
import com.betta.note.domain.NoteInfo;
import com.betta.note.domain.NoteInfoDelete;
import com.betta.note.domain.NoteVo;
import com.betta.note.mapper.NoteInfoMapper;
import com.betta.note.service.IContentService;
import com.betta.note.service.INoteInfoDeleteService;
import com.betta.note.service.INoteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 文件夹Service业务层处理
 *
 * @author ruoyi
 * @date 2024-06-06
 */
@Service
public class NoteInfoServiceImpl extends ServiceImpl<NoteInfoMapper,NoteInfo> implements INoteInfoService {
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
    @CreateByScope(value = "",adminAccessAll = false)
    public List<NoteInfo> selectNoteInfoList(NoteInfo noteInfo) {
        return noteInfoMapper.selectNoteInfoList(noteInfo);
    }

    @Override
    public List<NoteVo> selectNoteInfoDetailList(NoteInfo noteInfo) {
        return noteInfoMapper.selectNoteDetailList(noteInfo);
    }

    @Override
    public Long selectNoteInfoDetailCount(NoteInfo noteInfo) {
        return noteInfoMapper.selectNoteCount(noteInfo);
    }

    /**
     * 新增文件夹或笔记
     *
     * @param noteInfo 文件夹或笔记
     * @return 结果
     */
    @Override
    public Long insertNoteInfo(NoteInfo noteInfo) {
        //如果是笔记，创建content
        if(noteInfo.getIsLeaf()){
            Content noteContent = new Content();
            contentService.insertcontent(noteContent);
            noteInfo.setContentId(noteContent.getId());
        }
        noteInfoMapper.insertNoteInfo(noteInfo);
        return noteInfo.getId();
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
                    throw new ApiException("删除的文件夹有子文件，请先删除子文件");
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
    public List<TreeSelect> selectNoteTreeList(NoteInfo noteInfo) {
        noteInfo.setCreateBy(SecurityUtils.getUsername());
        List<TreeSelect> notes = noteInfoMapper.selectTreeSelect(noteInfo);
        //增加根节点
        TreeSelect root = new TreeSelect();
        root.setId(0L);
        root.setLabel("文件夹");
        root.setParentId(-1L);
        notes.add(root);
        return TreeUtil.wrapTreeDataToTreeList(notes, noteInfo.getParentId());
    }

    /**更新父节点
     *
     * @param ids
     * @param parentId
     */
    @Override
    public void updateParent(String[] ids, Long parentId) {
        NoteInfo parent = selectNoteInfoById(parentId);
        if (parentId!= 0 && parent == null) {
            throw new ApiException("父节点不合法");
        } else {
            noteInfoMapper.updateParent(ids, parentId);
        }

    }

    @Override
    public List<NoteInfo> selectNoteInfoByIds(String[] noteIds) {
        return noteInfoMapper.selectNoteInfoByIds(noteIds);
    }

    @Override
    @CreateByScope("")
    public List<NoteInfo> selectLast(int limit) {
        Map param = new HashMap();
        param.put("limit",limit);
        param.put("params",new HashMap<>());
        return noteInfoMapper.selectLast(param);
    }

    /**
     * 根据contentId查询笔记
     * @param contentId
     * @return
     */
    @Override
    public NoteInfo selectNoteByContent(Long contentId) {
        LambdaQueryWrapper<NoteInfo> wrapper = new LambdaQueryWrapper<>();
        //过滤文章ID
        wrapper.eq(NoteInfo::getContentId, contentId);
        //过滤创建者
        wrapper.like(NoteInfo::getCreateBy, SecurityUtils.getUsername());

        return noteInfoMapper.selectOne(wrapper);
    }


}
