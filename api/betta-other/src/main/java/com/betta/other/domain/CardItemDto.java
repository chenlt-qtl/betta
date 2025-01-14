package com.betta.other.domain;

import lombok.Data;

/**
 * 卡片项明细对象 card_item
 * 
 * @author chenlt
 * @date 2024-06-17
 */
@Data
public class CardItemDto extends CardItem
{
    private static final long serialVersionUID = 1L;

    /**
     * 扣卡内容
     */
    private String content;

}
