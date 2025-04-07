package com.betta.note.domain;

import com.betta.common.core.domain.SysBaseEntity;
import lombok.Data;
import com.betta.common.annotation.Excel;
import lombok.NoArgsConstructor;

/**
 * 历史对象 note_info_history
 * 
 * @author chenlt
 * @date 2024-06-13
 */
@Data
@NoArgsConstructor
public class History extends SysBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long noteId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String title;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String text;

    public History(Long noteId, String title, String text) {
        this.noteId = noteId;
        this.title = title;
        this.text = text;
    }
}
