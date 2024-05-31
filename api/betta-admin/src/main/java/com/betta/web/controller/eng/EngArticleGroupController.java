package com.betta.web.controller.eng;

import com.betta.eng.domain.EngArticleGroup;
import com.betta.eng.service.IEngArticleGroupService;
import com.betta.common.annotation.Log;
import com.betta.common.core.controller.BaseController;
import com.betta.common.core.domain.AjaxResult;
import com.betta.common.core.page.TableDataInfo;
import com.betta.common.enums.BusinessType;
import com.betta.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
