package com.betta.common.enums;

/**
 * 上传文件类型
 *
 * @author ruoyi
 */

public enum UploadFileType
{
    /**
     *公告
     */
    NOTICE("notice"),
    /**
     * 单词
     */
    WORD("word"),

    /**
     * 笔记
     */
    NOTE("note"),
    /**
     * 文章
     */
    ARTICLE("article"),

    /**
     * 视频
     */
    VIDEO("video");

    private String type;
    UploadFileType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
