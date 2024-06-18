package com.betta.card.service.impl;

import java.util.List;
import com.betta.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betta.card.mapper.CardItemMapper;
import com.betta.card.domain.CardItem;
import com.betta.card.service.ICardItemService;

/**
 * 卡片项明细Service业务层处理
 * 
 * @author chenlt
 * @date 2024-06-17
 */
@Service
public class CardItemServiceImpl implements ICardItemService 
{
    @Autowired
    private CardItemMapper cardItemMapper;

    /**
     * 查询卡片项明细
     * 
     * @param id 卡片项明细主键
     * @return 卡片项明细
     */
    @Override
    public CardItem selectCardItemById(Long id)
    {
        return cardItemMapper.selectCardItemById(id);
    }

    /**
     * 查询卡片项明细列表
     * 
     * @param cardItem 卡片项明细
     * @return 卡片项明细
     */
    @Override
    public List<CardItem> selectCardItemList(CardItem cardItem)
    {
        return cardItemMapper.selectCardItemList(cardItem);
    }

    /**
     * 新增卡片项明细
     * 
     * @param cardItem 卡片项明细
     * @return 结果
     */
    @Override
    public int insertCardItem(CardItem cardItem)
    {
        cardItem.setCreateTime(DateUtils.getNowDate());
        return cardItemMapper.insertCardItem(cardItem);
    }

    /**
     * 修改卡片项明细
     * 
     * @param cardItem 卡片项明细
     * @return 结果
     */
    @Override
    public int updateCardItem(CardItem cardItem)
    {
        cardItem.setUpdateTime(DateUtils.getNowDate());
        return cardItemMapper.updateCardItem(cardItem);
    }

    /**
     * 批量删除卡片项明细
     * 
     * @param ids 需要删除的卡片项明细主键
     * @return 结果
     */
    @Override
    public int deleteCardItemByIds(Long[] ids)
    {
        return cardItemMapper.deleteCardItemByIds(ids);
    }

    /**
     * 删除卡片项明细信息
     * 
     * @param id 卡片项明细主键
     * @return 结果
     */
    @Override
    public int deleteCardItemById(Long id)
    {
        return cardItemMapper.deleteCardItemById(id);
    }
}
