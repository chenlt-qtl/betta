package com.betta.web.controller.es;

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
import com.betta.es.domain.EsHistory;
import com.betta.es.service.IEsHistoryService;
import com.betta.common.utils.poi.ExcelUtil;
import com.betta.common.core.page.TableDataInfo;

/**
 * ES历史Controller
 * 
 * @author chenlt
 * @date 2024-06-17
 */
@RestController
@RequestMapping("/es/history")
public class EsHistoryController extends BaseController
{
    @Autowired
    private IEsHistoryService esHistoryService;

    /**
     * 查询ES历史列表
     */
    @PreAuthorize("@ss.hasPermi('es:history:list')")
    @GetMapping("/list")
    public TableDataInfo list(EsHistory esHistory)
    {
        startPage();
        List<EsHistory> list = esHistoryService.selectEsHistoryList(esHistory);
        return getDataTable(list);
    }

    /**
     * 导出ES历史列表
     */
    @PreAuthorize("@ss.hasPermi('es:history:export')")
    @Log(title = "ES历史", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EsHistory esHistory)
    {
        List<EsHistory> list = esHistoryService.selectEsHistoryList(esHistory);
        ExcelUtil<EsHistory> util = new ExcelUtil<EsHistory>(EsHistory.class);
        util.exportExcel(response, list, "ES历史数据");
    }

    /**
     * 获取ES历史详细信息
     */
    @PreAuthorize("@ss.hasPermi('es:history:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(esHistoryService.selectEsHistoryById(id));
    }

    /**
     * 新增ES历史
     */
    @PreAuthorize("@ss.hasPermi('es:history:add')")
    @Log(title = "ES历史", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EsHistory esHistory)
    {
        return toAjax(esHistoryService.insertEsHistory(esHistory));
    }

    /**
     * 修改ES历史
     */
    @PreAuthorize("@ss.hasPermi('es:history:edit')")
    @Log(title = "ES历史", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EsHistory esHistory)
    {
        return toAjax(esHistoryService.updateEsHistory(esHistory));
    }

    /**
     * 删除ES历史
     */
    @PreAuthorize("@ss.hasPermi('es:history:remove')")
    @Log(title = "ES历史", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(esHistoryService.deleteEsHistoryByIds(ids));
    }
}
