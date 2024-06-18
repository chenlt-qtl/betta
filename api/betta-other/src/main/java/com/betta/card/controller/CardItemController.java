package com.betta.card.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.betta.common.annotation.Log;
import com.betta.common.core.controller.BaseController;
import com.betta.common.core.domain.AjaxResult;
import com.betta.common.enums.BusinessType;
import com.betta.card.domain.CardItem;
import com.betta.card.service.ICardItemService;
import com.betta.common.utils.poi.ExcelUtil;
import com.betta.common.core.page.TableDataInfo;

/**
 * 卡片项明细Controller
 * 
 * @author chenlt
 * @date 2024-06-17
 */
@RestController
@RequestMapping("/card/item")
public class CardItemController extends BaseController
{
    @Autowired
    private ICardItemService cardItemService;

    /**
     * 查询卡片项明细列表
     */
    @PreAuthorize("@ss.hasPermi('card:item:list')")
    @GetMapping("/list")
    public TableDataInfo list(CardItem cardItem)
    {
        startPage();
        List<CardItem> list = cardItemService.selectCardItemList(cardItem);
        return getDataTable(list);
    }

    /**
     * 导出卡片项明细列表
     */
    @PreAuthorize("@ss.hasPermi('card:item:export')")
    @Log(title = "卡片项明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CardItem cardItem)
    {
        List<CardItem> list = cardItemService.selectCardItemList(cardItem);
        ExcelUtil<CardItem> util = new ExcelUtil<CardItem>(CardItem.class);
        util.exportExcel(response, list, "卡片项明细数据");
    }

    /**
     * 获取卡片项明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('card:item:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(cardItemService.selectCardItemById(id));
    }

    /**
     * 新增卡片项明细
     */
    @PreAuthorize("@ss.hasPermi('card:item:add')")
    @Log(title = "卡片项明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CardItem cardItem)
    {
        return toAjax(cardItemService.insertCardItem(cardItem));
    }

    /**
     * 修改卡片项明细
     */
    @PreAuthorize("@ss.hasPermi('card:item:edit')")
    @Log(title = "卡片项明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CardItem cardItem)
    {
        return toAjax(cardItemService.updateCardItem(cardItem));
    }

    /**
     * 删除卡片项明细
     */
    @PreAuthorize("@ss.hasPermi('card:item:remove')")
    @Log(title = "卡片项明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cardItemService.deleteCardItemByIds(ids));
    }
}
