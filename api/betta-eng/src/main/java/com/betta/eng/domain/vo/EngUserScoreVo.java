package com.betta.eng.domain.vo;

import com.betta.eng.domain.EngUserScore;
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

    /** 音频位置 */
    private String phMp3;

    /** 音标 */
    private String phonetics;

    /** 解释 */
    private String acceptation;

    /** 手动注释 */
    private String exchange;

    /** $column.columnComment */
    private String parts;
}
