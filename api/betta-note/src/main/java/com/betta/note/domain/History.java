package com.betta.note.domain;

import com.betta.common.core.domain.SysBaseEntity;
import lombok.Data;
import com.betta.common.annotation.Excel;

/**
 * 历史对象 note_info_history
 * 
 * @author chenlt
 * @date 2024-06-13
 */
@Data
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

}
