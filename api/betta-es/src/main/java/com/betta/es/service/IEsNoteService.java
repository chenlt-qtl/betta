package com.betta.es.service;

import com.betta.note.domain.NoteInfo;
import com.betta.note.domain.NoteVo;

import java.io.IOException;
import java.util.List;

public interface IEsNoteService {


    /**
     * 查询文件夹列表
     *
     * @return 文件夹集合
     */
    public List<NoteVo> search(String search) throws IOException;

    void createIndex(String user) throws IOException;

    /**
     * 新增文件
     *
     */
    public void insertNoteDoc(Long noteId);

    void insertNoteDocs(List<NoteVo> list, String userName);

    Long batchInsertDoc(String userName);

    /**
     * 修改文件
     *
     * @param noteInfo 文件
     * @return 结果
     */
    public int updateNoteDoc(NoteInfo noteInfo);

    /**
     * 批量删除文件
     *
     * @param ids 需要删除的文件主键集合
     * @return 结果
     */
    public int deleteNoteDocByIds(Long[] ids);

    void deleteIndex(String userName) throws IOException;
}
