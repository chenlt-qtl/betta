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
public class EngUserScore extends EngWord
{
    private static final long serialVersionUID = 1L;

    private Long id;

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

    private String sentence;

    private String sentenceAcceptation;

    private Long relId;//关联ID

    private Long articleId;//文章ID
}
