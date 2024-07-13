package com.betta.web.controller.system;

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
import com.betta.system.domain.SysHistory;
import com.betta.system.service.ISysHistoryService;
import com.betta.common.utils.poi.ExcelUtil;
import com.betta.common.core.page.TableDataInfo;

/**
 * 系统历史Controller
 * 
 * @author chenlt
 * @date 2024-07-13
 */
@RestController
@RequestMapping("/system/history")
public class SysHistoryController extends BaseController
{
    @Autowired
    private ISysHistoryService sysHistoryService;

    /**
     * 查询系统历史列表
     */
    @PreAuthorize("@ss.hasPermi('system:history:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysHistory sysHistory)
    {
        startPage();
        List<SysHistory> list = sysHistoryService.selectSysHistoryList(sysHistory);
        return getDataTable(list);
    }

    /**
     * 导出系统历史列表
     */
    @PreAuthorize("@ss.hasPermi('system:history:export')")
    @Log(title = "系统历史", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysHistory sysHistory)
    {
        List<SysHistory> list = sysHistoryService.selectSysHistoryList(sysHistory);
        ExcelUtil<SysHistory> util = new ExcelUtil<SysHistory>(SysHistory.class);
        util.exportExcel(response, list, "系统历史数据");
    }

    /**
     * 获取系统历史详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:history:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysHistoryService.selectSysHistoryById(id));
    }

    /**
     * 新增系统历史
     */
    @PreAuthorize("@ss.hasPermi('system:history:add')")
    @Log(title = "系统历史", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysHistory sysHistory)
    {
        return toAjax(sysHistoryService.insertSysHistory(sysHistory));
    }

    /**
     * 修改系统历史
     */
    @PreAuthorize("@ss.hasPermi('system:history:edit')")
    @Log(title = "系统历史", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysHistory sysHistory)
    {
        return toAjax(sysHistoryService.updateSysHistory(sysHistory));
    }

    /**
     * 删除系统历史
     */
    @PreAuthorize("@ss.hasPermi('system:history:remove')")
    @Log(title = "系统历史", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysHistoryService.deleteSysHistoryByIds(ids));
    }
}
