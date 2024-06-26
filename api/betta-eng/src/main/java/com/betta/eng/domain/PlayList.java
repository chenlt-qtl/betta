package com.betta.eng.domain;

import lombok.Data;
import com.betta.common.annotation.Excel;
import com.betta.common.core.domain.BaseEntity;

/**
 * 播放列表对象 eng_play_list
 * 
 * @author chenlt
 * @date 2024-06-25
 */
@Data
public class PlayList extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 句子ID */
    @Excel(name = "句子ID")
    private String sentenceIds;

}
