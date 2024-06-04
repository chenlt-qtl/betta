package com.betta.web.controller.eng;

import com.betta.common.annotation.Log;
import com.betta.common.core.controller.BaseController;
import com.betta.common.core.domain.AjaxResult;
import com.betta.common.core.page.TableDataInfo;
import com.betta.common.enums.BusinessType;
import com.betta.common.utils.poi.ExcelUtil;
import com.betta.eng.domain.EngWord;
import com.betta.eng.service.IEngWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 单词Controller
 * 
 * @author ruoyi
 * @date 2024-06-03
 */
@RestController
@RequestMapping("/eng/word")
public class EngWordController extends BaseController
{
    @Autowired
    private IEngWordService engWordService;

    /**
     * 查询单词列表
     */
    @PreAuthorize("@ss.hasPermi('eng:word:list')")
    @GetMapping("/list")
    public TableDataInfo list(EngWord engWord)
    {
        startPage();
        List<EngWord> list = engWordService.selectEngWordList(engWord);
        return getDataTable(list);
    }

    /**
     * 根据article查询
     */
    @PreAuthorize("@ss.hasPermi('eng:word:list')")
    @GetMapping("/list/{articleId}")
    public TableDataInfo list(@PathVariable Long articleId)
    {
        startPage();
        List<EngWord> list = engWordService.selectWordListByArticle(articleId);
        return getDataTable(list);
    }

    /**
     * 导出单词列表
     */
    @PreAuthorize("@ss.hasPermi('eng:word:export')")
    @Log(title = "单词", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EngWord engWord)
    {
        List<EngWord> list = engWordService.selectEngWordList(engWord);
        ExcelUtil<EngWord> util = new ExcelUtil<EngWord>(EngWord.class);
        util.exportExcel(response, list, "单词数据");
    }

    /**
     * 获取单词详细信息
     */
    @PreAuthorize("@ss.hasPermi('eng:word:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(engWordService.selectEngWordById(id));
    }

    /**
     * 新增单词
     */
    @PreAuthorize("@ss.hasPermi('eng:word:add')")
    @Log(title = "单词", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EngWord engWord)
    {
        return toAjax(engWordService.insertEngWord(engWord));
    }

    /**
     * 新增文章对应的单词
     * @param map
     * @param articleId
     * @return
     */
    @PreAuthorize("@ss.hasPermi('eng:word:add')")
    @Log(title = "单词", businessType = BusinessType.INSERT)
    @PostMapping("/{articleId}")
    public AjaxResult addByArticle(@RequestBody Map map, @PathVariable Long articleId)
    {
        List<String> words = (List<String>) map.get("words");
        engWordService.addByArticle(words,articleId);
        return AjaxResult.success();
    }


    /**
     * 修改单词
     */
    @PreAuthorize("@ss.hasPermi('eng:word:edit')")
    @Log(title = "单词", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EngWord engWord)
    {
        return toAjax(engWordService.updateEngWord(engWord));
    }

    /**
     * 删除单词
     */
    @PreAuthorize("@ss.hasPermi('eng:word:remove')")
    @Log(title = "单词", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        engWordService.deleteEngWordById(id);
        return AjaxResult.success();
    }
}
