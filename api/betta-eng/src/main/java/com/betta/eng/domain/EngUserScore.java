package com.betta.eng.domain;

import lombok.Data;
import com.betta.common.annotation.Excel;
import com.betta.common.core.domain.BaseEntity;

/**
 * 用户成绩对象 eng_user_score
 * 
 * @author chenlt
 * @date 2024-07-13
 */
@Data
public class EngUserScore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String user;

    /** 单词内容 */
    @Excel(name = "单词内容")
    private String wordName;

    /** 熟悉度越高越熟悉，0为最低 */
    @Excel(name = "熟悉度越高越熟悉，0为最低")
    private Long familiarity;

    /**  */
    @Excel(name = "")
    private String status;

    @Excel(name = "音标")
    private String phAm;

    /** 解释 */
    @Excel(name = "解释")
    private String acceptation;

    /** 手动注释 */
    @Excel(name = "手动注释")
    private String exchange;

    /** 音频位置 */
    @Excel(name = "音频位置")
    private String phAnMp3;

    private String sentence;

    private String sentenceAcceptation;
}
