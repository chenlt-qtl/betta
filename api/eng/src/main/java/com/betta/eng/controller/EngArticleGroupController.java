package com.betta.eng.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.betta.eng.domain.EngArticleGroup;
import com.betta.eng.service.IEngArticleGroupService;
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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 英语文章分组Controller
 * 
 * @author ruoyi
 * @date 2024-05-30
 */
@RestController
@RequestMapping("/eng/group")
public class EngArticleGroupController extends BaseController
{
    @Autowired
    private IEngArticleGroupService engArticleGroupService;

    /**
     * 查询英语文章分组列表
     */
//    @PreAuthorize("@ss.hasPermi('eng:group:list')")
    @GetMapping("/list")
    public TableDataInfo list(EngArticleGroup engArticleGroup)
    {
        startPage();
        List<EngArticleGroup> list = engArticleGroupService.selectEngArticleGroupList(engArticleGroup);
        return getDataTable(list);
    }

    /**
     * 导出英语文章分组列表
     */
    @PreAuthorize("@ss.hasPermi('eng:group:export')")
    @Log(title = "英语文章分组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EngArticleGroup engArticleGroup)
    {
        List<EngArticleGroup> list = engArticleGroupService.selectEngArticleGroupList(engArticleGroup);
        ExcelUtil<EngArticleGroup> util = new ExcelUtil<EngArticleGroup>(EngArticleGroup.class);
        util.exportExcel(response, list, "英语文章分组数据");
    }

    /**
     * 获取英语文章分组详细信息
     */
    @PreAuthorize("@ss.hasPermi('eng:group:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(engArticleGroupService.selectEngArticleGroupById(id));
    }

    /**
     * 新增英语文章分组
     */
    @PreAuthorize("@ss.hasPermi('eng:group:add')")
    @Log(title = "英语文章分组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EngArticleGroup engArticleGroup)
    {
        return toAjax(engArticleGroupService.insertEngArticleGroup(engArticleGroup));
    }

    /**
     * 修改英语文章分组
     */
    @PreAuthorize("@ss.hasPermi('eng:group:edit')")
    @Log(title = "英语文章分组", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EngArticleGroup engArticleGroup)
    {
        return toAjax(engArticleGroupService.updateEngArticleGroup(engArticleGroup));
    }

    /**
     * 删除英语文章分组
     */
    @PreAuthorize("@ss.hasPermi('eng:group:remove')")
    @Log(title = "英语文章分组", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(engArticleGroupService.deleteEngArticleGroupByIds(ids));
    }
}
