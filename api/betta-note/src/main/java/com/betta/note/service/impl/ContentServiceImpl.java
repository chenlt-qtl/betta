package com.betta.note.service.impl;

import java.util.Date;
import java.util.List;

import com.betta.common.utils.DateUtils;
import com.betta.note.domain.History;
import com.betta.note.domain.NoteInfo;
import com.betta.note.service.IHistoryService;
import com.betta.note.service.INoteInfoService;
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
public class ContentServiceImpl implements IContentService {
    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private IHistoryService historyService;

    @Autowired
    private INoteInfoService noteInfoService;

    /**
     * 查询笔记内容
     *
     * @param id 笔记内容主键
     * @return 笔记内容
     */
    @Override
    public Content selectcontentById(Long id) {
        return contentMapper.selectcontentById(id);
    }

    /**
     * 查询笔记内容列表
     *
     * @param content 笔记内容
     * @return 笔记内容
     */
    @Override
    public List<Content> selectcontentList(Content content) {
        return contentMapper.selectcontentList(content);
    }

    /**
     * 新增笔记内容
     *
     * @param content 笔记内容
     * @return 结果
     */
    @Override
    public int insertcontent(Content content) {
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
    public int updatecontent(Content content) {
        //查出数据库中的旧数据
        Content content_db = contentMapper.selectcontentById(content.getId());

        //如果内容跟原来差超过100个字符或者时间超过10分钟，则自动保存历史记录
        if (content_db.getText() == null || Math.abs(content.getText().length() - content_db.getText().length()) >= 100
                || content_db.getUpdateTime() == null
                || DateUtils.differentMinsByMillisecond(content_db.getUpdateTime(), new Date()) > 10) {
            //查出对应的note
            NoteInfo noteInfo = noteInfoService.selectNoteByContent(content.getId());
            if (noteInfo != null) {
                History history = new History(noteInfo.getId(), "", content_db.getText());
                historyService.inserthistory(history);
            }
        }
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
    public int deletecontentByIds(Long[] ids) {
        return contentMapper.deletecontentByIds(ids);
    }

    /**
     * 删除笔记内容信息
     *
     * @param id 笔记内容主键
     * @return 结果
     */
    @Override
    public int deletecontentById(Long id) {
        return contentMapper.deletecontentById(id);
    }
}
