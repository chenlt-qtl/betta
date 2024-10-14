package com.betta.web.controller.eng;

import com.betta.common.annotation.Log;
import com.betta.common.core.controller.BaseController;
import com.betta.common.core.domain.AjaxResult;
import com.betta.common.core.page.TableDataInfo;
import com.betta.common.enums.BusinessType;
import com.betta.eng.domain.EngWord;
import com.betta.eng.service.IEngWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
public class EngWordController extends BaseController {
    @Autowired
    private IEngWordService engWordService;

    /**
     * 查询单词列表
     */
    @PreAuthorize("@ss.hasPermi('eng:word:list')")
    @GetMapping("/list")
    public TableDataInfo list(EngWord engWord) {
        startPage();
        List<EngWord> list = engWordService.selectEngWordList(engWord);
        return getDataTable(list);
    }

    /**
     * 根据ID获取单词
     */
    @PreAuthorize("@ss.hasPermi('eng:word:query')")
    @GetMapping("/{id}")
    public AjaxResult getById(@PathVariable Long id) {
        EngWord word = engWordService.selectEngWordById(id);
        return AjaxResult.success(word);
    }

    /**
     * 查询生词本
     */
    @PreAuthorize("@ss.hasPermi('eng:word:list')")
    @GetMapping("/new")
    public TableDataInfo listNew(EngWord engWord) {
        startPage();
        List<EngWord> list = engWordService.selectNewList(engWord);
        return getDataTable(list);
    }

    /**
     * 根据article查询
     */
    @PreAuthorize("@ss.hasPermi('eng:word:list')")
    @GetMapping("/list/{articleId}")
    public TableDataInfo list(@PathVariable Long articleId) {
        startPage();
        List<EngWord> list = engWordService.selectWordListByArticle(articleId);
        return getDataTable(list);
    }

    /**
     * 获取单词详细信息
     */
    @PreAuthorize("@ss.hasPermi('eng:word:query')")
    @GetMapping
    public AjaxResult getInfo(String wordName) {
        return success(engWordService.getWord(wordName));
    }

    /**
     * 新增单词
     */
    @PreAuthorize("@ss.hasPermi('eng:word:add')")
    @Log(title = "单词", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EngWord engWord) {
        return toAjax(engWordService.insertEngWord(engWord));
    }

    /**
     * 更新文章对应的单词
     *
     * @param map
     * @param articleId
     * @return
     */
    @PreAuthorize("@ss.hasPermi('eng:word:add')")
    @Log(title = "单词", businessType = BusinessType.INSERT)
    @PostMapping("/{articleId}")
    public AjaxResult updateArticleWord(@RequestBody Map map, @PathVariable Long articleId) {
        List<String> words = (List<String>) map.get("words");
        engWordService.updateByArticle(words, articleId);
        return AjaxResult.success();
    }

    /**
     * 新增文章对应的单词
     *
     * @param articleId
     * @return
     */
    @PreAuthorize("@ss.hasPermi('eng:word:add')")
    @Log(title = "单词", businessType = BusinessType.INSERT)
    @PostMapping("/{articleId}/{wordName}")
    public AjaxResult addArticleWord(@PathVariable Long articleId,@PathVariable String wordName) {
        engWordService.addArticleWord(articleId,wordName);
        return AjaxResult.success();
    }


    @PreAuthorize("@ss.hasPermi('eng:word:query')")
    @GetMapping("/api/{wordName}")
    public AjaxResult getFromApi(@PathVariable String wordName) {
        return success(engWordService.getWordFromApi(wordName));
    }
    /**
     * 修改单词
     */
    @PreAuthorize("@ss.hasPermi('eng:word:edit')")
    @Log(title = "单词", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EngWord engWord) {
        return toAjax(engWordService.updateEngWord(engWord));
    }

    /**
     * 删除单词
     */
    @PreAuthorize("@ss.hasPermi('eng:word:remove')")
    @Log(title = "单词", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        engWordService.deleteEngWordById(id);
        return AjaxResult.success();
    }
}
