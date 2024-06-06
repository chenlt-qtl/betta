package com.betta.note.domain;

import lombok.Data;
import com.betta.common.annotation.Excel;
import com.betta.common.core.domain.BaseEntity;

/**
 * 文件夹对象 note_info
 * 
 * @author ruoyi
 * @date 2024-06-06
 */
@Data
public class NoteInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 内容ID */
    @Excel(name = "内容ID")
    private Long contentId;

    /** 标题 */
    @Excel(name = "标题")
    private String name;

    /** 父ID */
    @Excel(name = "父ID")
    private Long parentId;

    /** 标签 */
    @Excel(name = "标签")
    private String tag;

    /** 来源 */
    @Excel(name = "来源")
    private String source;

    /** 是否笔记，笔记1，文件夹0 */
    @Excel(name = "是否笔记，笔记1，文件夹0")
    private Boolean isLeaf;

    /** $column.columnComment */
    private String status;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String parentIds;

}
