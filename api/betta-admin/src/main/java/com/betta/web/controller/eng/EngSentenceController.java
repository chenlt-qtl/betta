package com.betta.web.controller.eng;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.betta.eng.service.IEngSentenceService;
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
import com.betta.eng.domain.EngSentence;
import com.betta.common.utils.poi.ExcelUtil;
import com.betta.common.core.page.TableDataInfo;

/**
 * 文章句子Controller
 * 
 * @author ruoyi
 * @date 2024-06-02
 */
@RestController
@RequestMapping("/eng/sentence")
public class EngSentenceController extends BaseController
{
    @Autowired
    private IEngSentenceService engSentenceService;

    /**
     * 查询文章句子列表
     */
    @PreAuthorize("@ss.hasPermi('eng:sentence:list')")
    @GetMapping("/list")
    public TableDataInfo list(EngSentence engSentence)
    {
        startPage();
        List<EngSentence> list = engSentenceService.selectEngSentenceList(engSentence);
        return getDataTable(list);
    }

    /**
     * 导出文章句子列表
     */
    @PreAuthorize("@ss.hasPermi('eng:sentence:export')")
    @Log(title = "文章句子", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EngSentence engSentence)
    {
        List<EngSentence> list = engSentenceService.selectEngSentenceList(engSentence);
        ExcelUtil<EngSentence> util = new ExcelUtil<EngSentence>(EngSentence.class);
        util.exportExcel(response, list, "文章句子数据");
    }

    /**
     * 获取文章句子详细信息
     */
    @PreAuthorize("@ss.hasPermi('eng:sentence:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(engSentenceService.selectEngSentenceById(id));
    }

    /**
     * 新增文章句子
     */
    @PreAuthorize("@ss.hasPermi('eng:sentence:add')")
    @Log(title = "文章句子", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EngSentence engSentence)
    {
        return toAjax(engSentenceService.insertEngSentence(engSentence));
    }

    /**
     * 修改文章句子
     */
    @PreAuthorize("@ss.hasPermi('eng:sentence:edit')")
    @Log(title = "文章句子", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EngSentence engSentence)
    {
        return toAjax(engSentenceService.updateEngSentence(engSentence));
    }

    /**
     * 删除文章句子
     */
    @PreAuthorize("@ss.hasPermi('eng:sentence:remove')")
    @Log(title = "文章句子", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(engSentenceService.deleteEngSentenceByIds(ids));
    }
}