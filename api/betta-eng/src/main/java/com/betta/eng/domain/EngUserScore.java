package com.betta.eng.domain;

import com.betta.common.core.domain.BaseEntity;
import lombok.Data;
import com.betta.common.annotation.Excel;

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

    private Long id;

    private String user;

    /** 单词内容 */
    @Excel(name = "单词内容")
    private String wordName;

    /** 熟悉度越高越熟悉 */
    @Excel(name = "熟悉度越高越熟悉")
    private Integer familiarity;

    /**  */
    @Excel(name = "")
    private String status;

}
