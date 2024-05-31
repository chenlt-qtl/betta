package com.betta.eng.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.betta.common.annotation.Excel;
import com.betta.common.core.domain.BaseEntity;

/**
 * 英语文章分组对象 eng_article_group
 * 
 * @author ruoyi
 * @date 2024-05-30
 */
@Data
public class EngArticleGroup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 手工注释 */
    @Excel(name = "手工注释")
    private String comment;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String status;

}
