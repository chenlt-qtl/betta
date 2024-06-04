package com.betta.eng.domain;

import lombok.Data;
import com.betta.common.annotation.Excel;
import com.betta.common.core.domain.BaseEntity;

/**
 * 爱词霸例句对象 eng_iciba_sentence
 * 
 * @author ruoyi
 * @date 2024-06-03
 */
@Data
public class EngIcibaSentence extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 单词ID */
    @Excel(name = "单词ID")
    private Long wordId;

    /** 句子内容 */
    @Excel(name = "句子内容")
    private String orig;

    /** 解释 */
    @Excel(name = "解释")
    private String trans;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String status;

}
