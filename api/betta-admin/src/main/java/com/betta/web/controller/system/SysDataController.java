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
import com.betta.system.domain.SysData;
import com.betta.system.service.ISysDataService;
import com.betta.common.utils.poi.ExcelUtil;
import com.betta.common.core.page.TableDataInfo;

/**
 * 系统数据Controller
 * 
 * @author ruoyi
 * @date 2025-03-15
 */
@RestController
@RequestMapping("/system/data")
public class SysDataController extends BaseController
{
    @Autowired
    private ISysDataService sysDataService;

    /**
     * 查询系统数据列表
     */
    @PreAuthorize("@ss.hasPermi('system:data:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysData sysData)
    {
        startPage();
        List<SysData> list = sysDataService.selectSysDataList(sysData);
        return getDataTable(list);
    }

    /**
     * 导出系统数据列表
     */
    @PreAuthorize("@ss.hasPermi('system:data:export')")
    @Log(title = "系统数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysData sysData)
    {
        List<SysData> list = sysDataService.selectSysDataList(sysData);
        ExcelUtil<SysData> util = new ExcelUtil<SysData>(SysData.class);
        util.exportExcel(response, list, "系统数据数据");
    }

    /**
     * 获取系统数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:data:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysDataService.selectSysDataById(id));
    }

    /**
     * 新增系统数据
     */
    @PreAuthorize("@ss.hasPermi('system:data:add')")
    @Log(title = "系统数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysData sysData)
    {
        return toAjax(sysDataService.insertSysData(sysData));
    }

    /**
     * 修改系统数据
     */
    @PreAuthorize("@ss.hasPermi('system:data:edit')")
    @Log(title = "系统数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysData sysData)
    {
        return toAjax(sysDataService.updateSysData(sysData));
    }

    /**
     * 删除系统数据
     */
    @PreAuthorize("@ss.hasPermi('system:data:remove')")
    @Log(title = "系统数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysDataService.deleteSysDataByIds(ids));
    }
}
