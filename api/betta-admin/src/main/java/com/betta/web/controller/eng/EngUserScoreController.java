package com.betta.web.controller.eng;

import com.betta.common.annotation.Log;
import com.betta.common.core.controller.BaseController;
import com.betta.common.core.domain.AjaxResult;
import com.betta.common.core.page.TableDataInfo;
import com.betta.common.enums.BusinessType;
import com.betta.common.utils.poi.ExcelUtil;
import com.betta.eng.domain.EngUserScore;
import com.betta.eng.service.IEngUserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户成绩Controller
 * 
 * @author chenlt
 * @date 2024-07-13
 */
@RestController
@RequestMapping("/eng/score")
public class EngUserScoreController extends BaseController
{
    @Autowired
    private IEngUserScoreService engUserScoreService;

    /**
     * 查询用户成绩列表
     */
    @PreAuthorize("@ss.hasPermi('eng:score:list')")
    @GetMapping("/list")
    public TableDataInfo list(EngUserScore engUserScore)
    {
        startPage();
        List<EngUserScore> list = engUserScoreService.selectEngUserScoreList(engUserScore);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('eng:score:list')")
    @GetMapping("/list/article/{articleId}/{limit}")
    public AjaxResult listByArticle(@PathVariable Long articleId,@PathVariable int limit)
    {
        List<EngUserScore> list = engUserScoreService.selectScoreByArticle(articleId,limit);
        return AjaxResult.success(list);
    }

    /**
     * 导出用户成绩列表
     */
    @PreAuthorize("@ss.hasPermi('eng:score:export')")
    @Log(title = "用户成绩", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EngUserScore engUserScore)
    {
        List<EngUserScore> list = engUserScoreService.selectEngUserScoreList(engUserScore);
        ExcelUtil<EngUserScore> util = new ExcelUtil<EngUserScore>(EngUserScore.class);
        util.exportExcel(response, list, "用户成绩数据");
    }

    /**
     * 获取用户成绩详细信息
     */
    @PreAuthorize("@ss.hasPermi('eng:score:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(engUserScoreService.selectEngUserScoreById(id));
    }

    /**
     * 新增用户成绩
     */
    @PreAuthorize("@ss.hasPermi('eng:score:add')")
    @Log(title = "用户成绩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EngUserScore engUserScore)
    {
        return toAjax(engUserScoreService.insertEngUserScore(engUserScore));
    }

    /**
     * 修改用户成绩
     */
    @PreAuthorize("@ss.hasPermi('eng:score:edit')")
    @Log(title = "用户成绩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EngUserScore engUserScore)
    {
        return toAjax(engUserScoreService.updateEngUserScore(engUserScore));
    }

    /**
     * 修改用户成绩
     */
    @PreAuthorize("@ss.hasPermi('eng:score:edit')")
    @Log(title = "用户成绩", businessType = BusinessType.UPDATE)
    @PutMapping("/batch")
    public AjaxResult batchUpdate(@RequestBody List<EngUserScore> engUserScoreList)
    {
        engUserScoreService.batchUpdate(engUserScoreList);
        return AjaxResult.success();
    }

    /**
     * 删除用户成绩
     */
    @PreAuthorize("@ss.hasPermi('eng:score:remove')")
    @Log(title = "用户成绩", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(engUserScoreService.deleteEngUserScoreByIds(ids));
    }
}
