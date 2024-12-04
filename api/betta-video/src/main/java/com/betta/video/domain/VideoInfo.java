package com.betta.video.domain;

import lombok.Data;
import com.betta.common.annotation.Excel;
import com.betta.common.core.domain.BaseEntity;

/**
 * 视频信息对象 video_info
 * 
 * @author ruoyi
 * @date 2024-12-04
 */
@Data
public class VideoInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long pid;

    /** 名称 */
    @Excel(name = "名称")
    private String title;

    /** 网址 */
    @Excel(name = "网址")
    private String url;

    /** 图片 */
    @Excel(name = "图片")
    private String img;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String status;

}
