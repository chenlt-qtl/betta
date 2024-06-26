package com.betta.web.controller.eng;

import com.betta.common.annotation.Log;
import com.betta.common.core.controller.BaseController;
import com.betta.common.core.domain.AjaxResult;
import com.betta.common.core.page.TableDataInfo;
import com.betta.common.enums.BusinessType;
import com.betta.common.utils.SecurityUtils;
import com.betta.common.utils.poi.ExcelUtil;
import com.betta.eng.domain.PlayList;
import com.betta.eng.service.IPlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 播放列表Controller
 * 
 * @author chenlt
 * @date 2024-06-25
 */
@RestController
@RequestMapping("/eng/playList")
public class PlayListController extends BaseController
{
    @Autowired
    private IPlayListService playListService;

    /**
     * 查询播放列表列表
     */
    @PreAuthorize("@ss.hasPermi('eng:playList:list')")
    @GetMapping("/list")
    public TableDataInfo list(PlayList playList)
    {
        startPage();
        List<PlayList> list = playListService.selectPlayListList(playList);
        return getDataTable(list);
    }

    /**
     * 查询播放列表列表
     */
    @PreAuthorize("@ss.hasPermi('eng:playList:list')")
    @GetMapping("/list/user")
    public AjaxResult listUser()
    {
        PlayList playList = new PlayList();
        playList.setUserName(SecurityUtils.getUsername());
        List<PlayList> list = playListService.selectPlayListList(playList);
        if(!list.isEmpty()){
            return AjaxResult.success(list.get(0));
        }
        return AjaxResult.success();
    }

    /**
     * 导出播放列表列表
     */
    @PreAuthorize("@ss.hasPermi('eng:playList:export')")
    @Log(title = "播放列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PlayList playList)
    {
        List<PlayList> list = playListService.selectPlayListList(playList);
        ExcelUtil<PlayList> util = new ExcelUtil<PlayList>(PlayList.class);
        util.exportExcel(response, list, "播放列表数据");
    }

    /**
     * 获取播放列表详细信息
     */
    @PreAuthorize("@ss.hasPermi('eng:playList:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(playListService.selectPlayListById(id));
    }

    /**
     * 新增播放列表
     */
    @PreAuthorize("@ss.hasPermi('eng:playList:add')")
    @Log(title = "播放列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PlayList playList)
    {
        playList.setUserName(SecurityUtils.getUsername());
        return toAjax(playListService.insertPlayList(playList));
    }

    /**
     * 修改播放列表
     */
    @PreAuthorize("@ss.hasPermi('eng:playList:edit')")
    @Log(title = "播放列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PlayList playList)
    {
        playList.setUserName(SecurityUtils.getUsername());
        return toAjax(playListService.updatePlayList(playList));
    }

    /**
     * 删除播放列表
     */
    @PreAuthorize("@ss.hasPermi('eng:playList:remove')")
    @Log(title = "播放列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(playListService.deletePlayListByIds(ids));
    }
}
