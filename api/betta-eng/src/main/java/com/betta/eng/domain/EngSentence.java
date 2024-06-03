package com.betta.eng.domain;

import lombok.Data;
import com.betta.common.annotation.Excel;
import com.betta.common.core.domain.BaseEntity;

/**
 * 文章句子对象 eng_sentence
 * 
 * @author ruoyi
 * @date 2024-06-02
 */
@Data
public class EngSentence extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 文章ID */
    @Excel(name = "文章ID")
    private Long articleId;

    /** 句子内容 */
    @Excel(name = "句子内容")
    private String content;

    /** 解释 */
    @Excel(name = "解释")
    private String acceptation;

    /** 序号 */
    @Excel(name = "序号")
    private Long idx;

    /** 图片 */
    @Excel(name = "图片")
    private String picture;

    /** 音频 */
    @Excel(name = "音频")
    private String mp3;

    /** MP3开始结束时间 */
    @Excel(name = "MP3开始结束时间")
    private String mp3Time;

    /** $column.columnComment */
    private String status;

}
