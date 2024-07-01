package com.betta.other.service;

import com.betta.other.domain.CardItem;

import java.util.List;

/**
 * 卡片项明细Service接口
 * 
 * @author chenlt
 * @date 2024-06-17
 */
public interface ICardItemService 
{
    /**
     * 查询卡片项明细
     * 
     * @param id 卡片项明细主键
     * @return 卡片项明细
     */
    public CardItem selectCardItemById(Long id);

    /**
     * 查询卡片项明细列表
     * 
     * @param cardItem 卡片项明细
     * @return 卡片项明细集合
     */
    public List<CardItem> selectCardItemList(CardItem cardItem);

    /**
     * 新增卡片项明细
     * 
     * @param cardItem 卡片项明细
     * @return 结果
     */
    public int insertCardItem(CardItem cardItem);

    /**
     * 修改卡片项明细
     * 
     * @param cardItem 卡片项明细
     * @return 结果
     */
    public int updateCardItem(CardItem cardItem);

    /**
     * 批量删除卡片项明细
     * 
     * @param ids 需要删除的卡片项明细主键集合
     * @return 结果
     */
    public int deleteCardItemByIds(Long[] ids);

    /**
     * 删除卡片项明细信息
     * 
     * @param id 卡片项明细主键
     * @return 结果
     */
    public int deleteCardItemById(Long id);
}
