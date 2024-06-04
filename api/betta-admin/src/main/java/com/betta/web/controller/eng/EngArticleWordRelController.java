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
import com.betta.eng.domain.EngArticleWordRel;
import com.betta.eng.service.IEngArticleWordRelService;
import com.betta.common.utils.poi.ExcelUtil;
import com.betta.common.core.page.TableDataInfo;

/**
 * 文章单词关系Controller
 * 
 * @author ruoyi
 * @date 2024-06-03
 */
@RestController
@RequestMapping("/eng/articleWordRel")
public class EngArticleWordRelController extends BaseController
{
    @Autowired
    private IEngArticleWordRelService engArticleWordRelService;

    /**
     * 查询文章单词关系列表
     */
    @PreAuthorize("@ss.hasPermi('eng:articleWordRel:list')")
    @GetMapping("/list")
    public TableDataInfo list(EngArticleWordRel engArticleWordRel)
    {
        startPage();
        List<EngArticleWordRel> list = engArticleWordRelService.selectEngArticleWordRelList(engArticleWordRel);
        return getDataTable(list);
    }

    /**
     * 导出文章单词关系列表
     */
    @PreAuthorize("@ss.hasPermi('eng:articleWordRel:export')")
    @Log(title = "文章单词关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EngArticleWordRel engArticleWordRel)
    {
        List<EngArticleWordRel> list = engArticleWordRelService.selectEngArticleWordRelList(engArticleWordRel);
        ExcelUtil<EngArticleWordRel> util = new ExcelUtil<EngArticleWordRel>(EngArticleWordRel.class);
        util.exportExcel(response, list, "文章单词关系数据");
    }

    /**
     * 获取文章单词关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('eng:articleWordRel:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(engArticleWordRelService.selectEngArticleWordRelById(id));
    }

    /**
     * 新增文章单词关系
     */
    @PreAuthorize("@ss.hasPermi('eng:articleWordRel:add')")
    @Log(title = "文章单词关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EngArticleWordRel engArticleWordRel)
    {
        return toAjax(engArticleWordRelService.insertEngArticleWordRel(engArticleWordRel));
    }

    /**
     * 修改文章单词关系
     */
    @PreAuthorize("@ss.hasPermi('eng:articleWordRel:edit')")
    @Log(title = "文章单词关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EngArticleWordRel engArticleWordRel)
    {
        return toAjax(engArticleWordRelService.updateEngArticleWordRel(engArticleWordRel));
    }

    /**
     * 删除文章单词关系
     */
    @PreAuthorize("@ss.hasPermi('eng:articleWordRel:remove')")
    @Log(title = "文章单词关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(engArticleWordRelService.deleteEngArticleWordRelByIds(ids));
    }
}
