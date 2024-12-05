package com.betta.web.controller.video;

import com.betta.common.annotation.Log;
import com.betta.common.core.controller.BaseController;
import com.betta.common.core.domain.AjaxResult;
import com.betta.common.core.page.TableDataInfo;
import com.betta.common.enums.BusinessType;
import com.betta.video.domain.VideoChildDto;
import com.betta.video.domain.VideoInfo;
import com.betta.video.domain.VideoListVo;
import com.betta.video.service.IVideoInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 视频信息Controller
 * 
 * @author ruoyi
 * @date 2024-12-04
 */
@RestController
@RequestMapping("/video")
public class VideoInfoController extends BaseController
{
    @Autowired
    private IVideoInfoService videoInfoService;

    /**
     * 查询视频信息列表
     */
    @PreAuthorize("@ss.hasPermi('video:video:list')")
    @GetMapping("/list")
    public TableDataInfo list(VideoInfo videoInfo)
    {
        startPage();
        List<VideoListVo> list = videoInfoService.selectVideoInfoList(videoInfo);
        return getDataTable(list);
    }

    @ApiOperation("列出所有title和pid")
    @PreAuthorize("@ss.hasPermi('video:video:list')")
    @GetMapping("/list/brief")
    public AjaxResult listTitle(@RequestParam(required = false) char isLeaf)
    {
        List<VideoInfo> list = videoInfoService.selectVideoInfoBrief(isLeaf);
        return success(list);
    }


    @ApiOperation("根据pid查询下一级子视频")
    @PreAuthorize("@ss.hasPermi('video:video:list')")
    @GetMapping("/{pid}/children")
    public AjaxResult listChilren(@PathVariable Long pid)
    {
        return success(videoInfoService.listChildren(pid));
    }

    /**
     * 获取视频信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('video:video:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(videoInfoService.getById(id));
    }

    /**
     * 新增视频信息
     */
    @PreAuthorize("@ss.hasPermi('video:video:add')")
    @Log(title = "视频信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VideoInfo videoInfo)
    {
        return toAjax(videoInfoService.insertVideoInfo(videoInfo));
    }

    /**
     * 新增子视频信息
     */
    @ApiOperation("增加子视频信息")
    @PreAuthorize("@ss.hasPermi('video:video:add')")
    @Log(title = "视频信息", businessType = BusinessType.INSERT)
    @PostMapping("/children")
    public AjaxResult addChildren(@RequestBody VideoChildDto videoInfo)
    {
        return toAjax(videoInfoService.addChildren(videoInfo));
    }

    /**
     * 修改视频信息
     */
    @PreAuthorize("@ss.hasPermi('video:video:edit')")
    @Log(title = "视频信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VideoInfo videoInfo)
    {
        return toAjax(videoInfoService.updateVideoInfo(videoInfo));
    }

    /**
     * 删除视频信息
     */
    @PreAuthorize("@ss.hasPermi('video:video:remove')")
    @Log(title = "视频信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(videoInfoService.deleteVideoInfoByIds(ids));
    }
}
