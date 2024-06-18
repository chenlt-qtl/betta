package com.betta.card.domain;

import lombok.Data;
import com.betta.common.annotation.Excel;
import com.betta.common.core.domain.BaseEntity;

/**
 * 卡片项明细对象 card_item
 * 
 * @author chenlt
 * @date 2024-06-17
 */
@Data
public class CardItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 类型 */
    @Excel(name = "类型")
    private Long type;

    /** 值 */
    @Excel(name = "值")
    private Long value;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

}
