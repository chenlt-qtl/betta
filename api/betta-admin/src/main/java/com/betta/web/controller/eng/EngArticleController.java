package com.betta.web.controller.eng;

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
import com.betta.eng.domain.EngArticle;
import com.betta.eng.service.IEngArticleService;
import com.betta.common.utils.poi.ExcelUtil;
import com.betta.common.core.page.TableDataInfo;

/**
 * 英语文章Controller
 * 
 * @author ruoyi
 * @date 2024-05-31
 */
@RestController
@RequestMapping("/eng/article")
public class EngArticleController extends BaseController
{
    @Autowired
    private IEngArticleService engArticleService;

    /**
     * 查询英语文章列表
     */
    @PreAuthorize("@ss.hasPermi('eng:article:list')")
    @GetMapping("/list")
    public TableDataInfo list(EngArticle engArticle)
    {
        startPage();
        List<EngArticle> list = engArticleService.selectEngArticleList(engArticle);
        return getDataTable(list);
    }

    /**
     * 导出英语文章列表
     */
    @PreAuthorize("@ss.hasPermi('eng:article:export')")
    @Log(title = "英语文章", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EngArticle engArticle)
    {
        List<EngArticle> list = engArticleService.selectEngArticleList(engArticle);
        ExcelUtil<EngArticle> util = new ExcelUtil<EngArticle>(EngArticle.class);
        util.exportExcel(response, list, "英语文章数据");
    }

    /**
     * 获取英语文章详细信息
     */
    @PreAuthorize("@ss.hasPermi('eng:article:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(engArticleService.selectEngArticleById(id));
    }

    /**
     * 新增英语文章
     */
    @PreAuthorize("@ss.hasPermi('eng:article:add')")
    @Log(title = "英语文章", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EngArticle engArticle)
    {
        return toAjax(engArticleService.insertEngArticle(engArticle));
    }

    /**
     * 修改英语文章
     */
    @PreAuthorize("@ss.hasPermi('eng:article:edit')")
    @Log(title = "英语文章", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EngArticle engArticle)
    {
        return toAjax(engArticleService.updateEngArticle(engArticle));
    }

    /**
     * 删除英语文章
     */
    @PreAuthorize("@ss.hasPermi('eng:article:remove')")
    @Log(title = "英语文章", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        return toAjax(engArticleService.deleteEngArticleById(id));
    }
}
