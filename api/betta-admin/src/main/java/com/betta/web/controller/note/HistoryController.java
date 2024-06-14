package com.betta.web.controller.note;

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
import com.betta.note.domain.History;
import com.betta.note.service.IHistoryService;
import com.betta.common.utils.poi.ExcelUtil;
import com.betta.common.core.page.TableDataInfo;

/**
 * 历史Controller
 * 
 * @author chenlt
 * @date 2024-06-13
 */
@RestController
@RequestMapping("/note/history")
public class HistoryController extends BaseController
{
    @Autowired
    private IHistoryService historyService;

    /**
     * 查询历史列表
     */
    @PreAuthorize("@ss.hasPermi('note:history:list')")
    @GetMapping("/list/{type}")
    public TableDataInfo list(History history,@PathVariable String type)
    {
        startPage();
        List<History> list = historyService.selecthistoryList(history,type);
        return getDataTable(list);
    }


    /**
     * 获取历史详细信息
     */
    @PreAuthorize("@ss.hasPermi('note:history:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(historyService.selecthistoryById(id));
    }

    /**
     * 新增历史
     */
    @PreAuthorize("@ss.hasPermi('note:history:add')")
    @Log(title = "历史", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody History history)
    {
        return toAjax(historyService.inserthistory(history));
    }

    /**
     * 修改历史
     */
    @PreAuthorize("@ss.hasPermi('note:history:edit')")
    @Log(title = "历史", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody History history)
    {
        return toAjax(historyService.updatehistory(history));
    }

    /**
     * 删除历史
     */
    @PreAuthorize("@ss.hasPermi('note:history:remove')")
    @Log(title = "历史", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(historyService.deletehistoryByIds(ids));
    }
}
