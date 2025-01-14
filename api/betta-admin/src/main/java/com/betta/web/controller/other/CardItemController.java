package com.betta.web.controller.other;

import com.betta.common.annotation.Log;
import com.betta.common.core.controller.BaseController;
import com.betta.common.core.domain.AjaxResult;
import com.betta.common.core.page.TableDataInfo;
import com.betta.common.enums.BusinessType;
import com.betta.common.utils.DictUtils;
import com.betta.common.utils.StringUtils;
import com.betta.common.utils.poi.ExcelUtil;
import com.betta.other.domain.CardItem;
import com.betta.other.domain.CardItemDto;
import com.betta.other.service.ICardItemService;
import com.betta.system.domain.SysHistory;
import com.betta.system.service.ISysHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 卡片项明细Controller
 *
 * @author chenlt
 * @date 2024-06-17
 */
@RestController
@RequestMapping("/card/item")
public class CardItemController extends BaseController {
    @Autowired
    private ICardItemService cardItemService;

    @Autowired
    private ISysHistoryService sysHistoryService;

    /**
     * 查询卡片项明细列表
     */
    @PreAuthorize("@ss.hasPermi('card:item:list')")
    @GetMapping("/list")
    public TableDataInfo list(CardItem cardItem) {
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
    public void export(HttpServletResponse response, CardItem cardItem) {
        List<CardItem> list = cardItemService.selectCardItemList(cardItem);
        ExcelUtil<CardItem> util = new ExcelUtil<CardItem>(CardItem.class);
        util.exportExcel(response, list, "卡片项明细数据");
    }

    /**
     * 获取卡片项明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('card:item:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(cardItemService.selectCardItemById(id));
    }

    /**
     * 新增卡片项明细
     */
    @PreAuthorize("@ss.hasPermi('card:item:add')")
    @Log(title = "卡片项明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CardItem cardItem) {
        return toAjax(cardItemService.insertCardItem(cardItem));
    }

    /**
     * 修改卡片项明细
     */
    @PreAuthorize("@ss.hasPermi('card:item:edit')")
    @Log(title = "卡片项明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CardItemDto cardItem) {
        CardItem old = cardItemService.selectCardItemById(cardItem.getId());
        cardItemService.updateCardItem(cardItem);
        //记录历史记录getDictValue
        String dictLabel = DictUtils.getDictLabel("card_type", cardItem.getType().toString());
        if (StringUtils.isBlank(dictLabel)) {//为空表示是加减卡 记录历史
            SysHistory history = new SysHistory();
            String type = DictUtils.getDictValue("history_type", "card");
            history.setValue(cardItem.getName() + ": " + (cardItem.getValue() - old.getValue())+" 余: "+cardItem.getValue() +
            (StringUtils.isNotBlank(cardItem.getContent())?", [ "+cardItem.getContent()+" ]":""));
            history.setType(Integer.parseInt(type));
            sysHistoryService.insertSysHistory(history);
        }
        return AjaxResult.success();
    }

    /**
     * 删除卡片项明细
     */
    @PreAuthorize("@ss.hasPermi('card:item:remove')")
    @Log(title = "卡片项明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(cardItemService.deleteCardItemByIds(ids));
    }
}
