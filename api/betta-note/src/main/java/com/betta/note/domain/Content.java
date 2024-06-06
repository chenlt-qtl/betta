package com.betta.note.domain;

import lombok.Data;
import com.betta.common.annotation.Excel;
import com.betta.common.core.domain.BaseEntity;

/**
 * 笔记内容对象 note_content
 * 
 * @author ruoyi
 * @date 2024-06-06
 */
@Data
public class Content extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 内容 */
    @Excel(name = "内容")
    private String text;

}
