package com.betta.web.controller.eng;

import com.betta.common.annotation.Log;
import com.betta.common.core.controller.BaseController;
import com.betta.common.core.domain.AjaxResult;
import com.betta.common.core.page.TableDataInfo;
import com.betta.common.enums.BusinessType;
import com.betta.eng.domain.EngSentence;
import com.betta.eng.domain.EngUserScore;
import com.betta.eng.domain.vo.EngUserScoreVo;
import com.betta.eng.service.IEngSentenceService;
import com.betta.eng.service.IEngUserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 用户成绩Controller
 *
 * @author chenlt
 * @date 2024-07-13
 */
@RestController
@RequestMapping("/eng/score")
public class EngUserScoreController extends BaseController {
    @Autowired
    private IEngUserScoreService engUserScoreService;

    @Autowired
    private IEngSentenceService engSentenceService;

    /**
     * 查询用户成绩列表
     */
    @PreAuthorize("@ss.hasPermi('eng:score:list')")
    @GetMapping("/list")
    public TableDataInfo list(EngUserScore engUserScore) {
        startPage();
        List<EngUserScore> list = engUserScoreService.selectEngUserScoreList(engUserScore);
        return getDataTable(list);
    }

    /**
     * 用户单词对应数据
     *
     * @return
     */
    @PreAuthorize("@ss.hasPermi('eng:score:list')")
    @GetMapping("/list/user")
    public TableDataInfo listByUser(EngUserScoreVo userScore, boolean withSentence) {
        startPage();
        List<EngUserScoreVo> list = engUserScoreService.selectEngUserScoreVoList(userScore);
        if (withSentence) {
            //查询对应的句子
            EngSentence engSentence = new EngSentence();
            if (!Objects.isNull(userScore.getArticleId())) {
                engSentence.setArticleId(userScore.getArticleId());
            }
            list.forEach(engUserScore -> {
                engSentence.setContent(engUserScore.getWordName());
                List<EngSentence> engSentences = engSentenceService.selectEngSentenceList(engSentence);
                if (!engSentences.isEmpty()) {
                    engUserScore.setSentence(engSentences.get(0).getContent());
                    engUserScore.setSentenceAcceptation(engSentences.get(0).getAcceptation());
                }
            });
        }
        return getDataTable(list);
    }

    /**
     * 新增用户成绩
     */
    @PreAuthorize("@ss.hasPermi('eng:score:add')")
    @Log(title = "用户成绩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EngUserScore engUserScore) {
        return toAjax(engUserScoreService.save(engUserScore));
    }

    /**
     * 修改用户成绩
     */
    @PreAuthorize("@ss.hasPermi('eng:score:edit')")
    @Log(title = "用户成绩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EngUserScore engUserScore) {
        return toAjax(engUserScoreService.updateById(engUserScore));
    }

    /**
     * 批量修改用户成绩
     */
    @PreAuthorize("@ss.hasPermi('eng:score:edit')")
    @Log(title = "用户成绩", businessType = BusinessType.UPDATE)
    @PutMapping("/batch")
    public AjaxResult batchUpdate(@RequestBody List<EngUserScore> engUserScoreList) {
        engUserScoreService.batchUpdate(engUserScoreList);
        return AjaxResult.success();
    }

    /**
     * 删除用户成绩
     */
    @PreAuthorize("@ss.hasPermi('eng:score:remove')")
    @Log(title = "用户成绩", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(engUserScoreService.removeBatchByIds(Arrays.asList(ids)));
    }
}
