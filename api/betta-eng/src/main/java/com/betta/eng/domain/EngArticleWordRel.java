package com.betta.eng.domain;

import lombok.Data;
import com.betta.common.annotation.Excel;
import com.betta.common.core.domain.BaseEntity;

/**
 * 文章单词关系对象 eng_article_word_rel
 * 
 * @author ruoyi
 * @date 2024-06-03
 */
@Data
public class EngArticleWordRel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 如果是从查询里加入，文章ID为0 */
    @Excel(name = "如果是从查询里加入，文章ID为0")
    private Long articleId;

    private String wordName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String status;

}
