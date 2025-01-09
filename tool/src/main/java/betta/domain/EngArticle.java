package betta.domain;


import lombok.Data;

import java.util.List;

/**
 * 英语文章对象 eng_article
 * 
 * @author ruoyi
 * @date 2024-05-31
 */
@Data
public class EngArticle {
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    private Long groupId;

    private String groupName;

    /** 图片位置 */
    private String picture;

    /** 音频位置 */
    private String mp3;

    /** 标题 */
    private String title;

    /** 手工注释 */
    private String comment;

    /** $column.columnComment */
    private String status;

}
