package com.betta.note.domain;

import com.betta.common.utils.bean.BeanUtils;
import lombok.Data;
import com.betta.common.annotation.Excel;
import com.betta.common.core.domain.BaseEntity;

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

    /**  */
    @Excel(name = "")
    private Long noteId;

    /**  */
    @Excel(name = "")
    private String name;

    /**  */
    @Excel(name = "")
    private Long parentId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long isLeaf;

    /**  */
    @Excel(name = "")
    private String tag;

    /**  */
    @Excel(name = "")
    private String source;

    /**  */
    private String text;

}
