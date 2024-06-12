package com.betta.note.domain;

import lombok.Data;
import com.betta.common.annotation.Excel;
import com.betta.common.core.domain.BaseEntity;

/**
 * 收藏对象 note_favorite
 * 
 * @author chenlt
 * @date 2024-06-12
 */
@Data
public class Favorite extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 笔记ID，用逗号分开 */
    @Excel(name = "笔记ID，用逗号分开")
    private String noteIds;

}
