package com.betta.web.controller.note;

import com.betta.common.annotation.Log;
import com.betta.common.core.controller.BaseController;
import com.betta.common.core.domain.AjaxResult;
import com.betta.common.core.page.TableDataInfo;
import com.betta.common.enums.BusinessType;
import com.betta.common.utils.poi.ExcelUtil;
import com.betta.note.domain.Content;
import com.betta.note.service.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 笔记内容Controller
 * 
 * @author ruoyi
 * @date 2024-06-06
 */
@RestController
@RequestMapping("/note/content")
public class ContentController extends BaseController
{
    @Autowired
    private IContentService contentService;

    /**
     * 查询笔记内容列表
     */
    @PreAuthorize("@ss.hasPermi('note:content:list')")
    @GetMapping("/list")
    public TableDataInfo list(Content content)
    {
        startPage();
        List<Content> list = contentService.selectcontentList(content);
        return getDataTable(list);
    }

    /**
     * 导出笔记内容列表
     */
    @PreAuthorize("@ss.hasPermi('note:content:export')")
    @Log(title = "笔记内容", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Content content)
    {
        List<Content> list = contentService.selectcontentList(content);
        ExcelUtil<Content> util = new ExcelUtil<Content>(Content.class);
        util.exportExcel(response, list, "笔记内容数据");
    }

    /**
     * 获取笔记内容详细信息
     */
    @PreAuthorize("@ss.hasPermi('note:content:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(contentService.selectcontentById(id));
    }

    /**
     * 新增笔记内容
     */
    @PreAuthorize("@ss.hasPermi('note:content:add')")
    @Log(title = "笔记内容", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Content content)
    {
        return toAjax(contentService.insertcontent(content));
    }

    /**
     * 修改笔记内容
     */
    @PreAuthorize("@ss.hasPermi('note:content:edit')")
    @Log(title = "笔记内容", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Content content)
    {
        return toAjax(contentService.updatecontent(content));
    }

    /**
     * 删除笔记内容
     */
    @PreAuthorize("@ss.hasPermi('note:content:remove')")
    @Log(title = "笔记内容", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(contentService.deletecontentByIds(ids));
    }
}
