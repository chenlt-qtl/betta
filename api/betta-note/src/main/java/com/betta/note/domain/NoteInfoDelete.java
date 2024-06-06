package com.betta.note.domain;

import lombok.Data;
import com.betta.common.annotation.Excel;
import com.betta.common.core.domain.BaseEntity;
import org.springframework.beans.BeanUtils;

/**
 * 删除历史对象 note_info_delete
 * 
 * @author ruoyi
 * @date 2024-06-06
 */
@Data
public class NoteInfoDelete extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    public NoteInfoDelete(){

    }

    public NoteInfoDelete(NoteInfo note){
        BeanUtils.copyProperties(note,this);
        this.setNoteId(note.getId());
        this.setId(null);
    }
    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long noteId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String name;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long parentId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String tag;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String source;

    /** $column.columnComment */
    private String text;

}
