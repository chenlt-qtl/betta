package com.betta.eng.domain;

import com.betta.common.annotation.Excel;
import com.betta.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * 英语文章对象 eng_article
 * 
 * @author ruoyi
 * @date 2024-05-31
 */
@Data
public class EngArticle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long groupId;

    private String groupName;

    /** 图片位置 */
    @Excel(name = "图片位置")
    private String picture;

    /** 音频位置 */
    @Excel(name = "音频位置")
    private String mp3;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 手工注释 */
    @Excel(name = "手工注释")
    private String comment;

    /** $column.columnComment */
    private String status;

    /** 文章句子信息 */
    private List<EngSentence> engSentenceList;

}
