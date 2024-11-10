package com.betta.eng.domain;

import com.betta.common.utils.StringUtils;
import lombok.Data;
import com.betta.common.annotation.Excel;
import com.betta.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 单词对象 eng_word
 * 
 * @author ruoyi
 * @date 2024-06-03
 */
@Data
public class EngWord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 单词内容 */
    @Excel(name = "单词内容")
    private String wordName;

    /** 音频位置 */
    @Excel(name = "音频位置")
    private String phMp3;

    /** 音标 */
    @Excel(name = "音标")
    private String phonetics;

    /** 解释 */
    @Excel(name = "解释")
    private String acceptation;

    /** 手动注释 */
    @Excel(name = "手动注释")
    private String exchange;

    /** $column.columnComment */
    private String parts;

    /** $column.columnComment */
    private String status;

}
