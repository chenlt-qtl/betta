package com.betta.eng.domain;

import lombok.Data;

/**
 * 用户成绩对象 eng_user_score
 * 
 * @author chenlt
 * @date 2024-07-13
 */
@Data
public class EngUserScoreVo extends EngUserScore
{
    private static final long serialVersionUID = 1L;

    private Long relId;

    private Long wordId;

    private Long articleId;//文章ID

    private String sentence;

    private String sentenceAcceptation;
}
