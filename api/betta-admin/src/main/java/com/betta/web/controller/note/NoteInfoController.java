package com.betta.web.controller.note;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.betta.common.core.domain.entity.SysDept;
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
import com.betta.note.domain.NoteInfo;
import com.betta.note.service.INoteInfoService;
import com.betta.common.utils.poi.ExcelUtil;
import com.betta.common.core.page.TableDataInfo;

/**
 * 文件夹Controller
 * 
 * @author ruoyi
 * @date 2024-06-06
 */
@RestController
@RequestMapping("/note/noteInfo")
public class NoteInfoController extends BaseController
{
    @Autowired
    private INoteInfoService noteInfoService;

    /**
     * 获取部门树列表
     */
    @PreAuthorize("@ss.hasPermi('note:noteInfo:list')")
    @GetMapping("/tree")
    public AjaxResult noteTree(NoteInfo noteInfo)
    {
        return success(noteInfoService.selectNoteTreeList(noteInfo));
    }

    /**
     * 查询文件夹列表
     */
    @PreAuthorize("@ss.hasPermi('note:noteInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(NoteInfo noteInfo)
    {
        startPage();
        List<NoteInfo> list = noteInfoService.selectNoteInfoList(noteInfo);
        return getDataTable(list);
    }

    /**
     * 导出文件夹列表
     */
    @PreAuthorize("@ss.hasPermi('note:noteInfo:export')")
    @Log(title = "文件夹", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NoteInfo noteInfo)
    {
        List<NoteInfo> list = noteInfoService.selectNoteInfoList(noteInfo);
        ExcelUtil<NoteInfo> util = new ExcelUtil<NoteInfo>(NoteInfo.class);
        util.exportExcel(response, list, "文件夹数据");
    }

    /**
     * 获取文件夹详细信息
     */
    @PreAuthorize("@ss.hasPermi('note:noteInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(noteInfoService.selectNoteInfoById(id));
    }

    /**
     * 新增文件夹
     */
    @PreAuthorize("@ss.hasPermi('note:noteInfo:add')")
    @Log(title = "文件夹", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NoteInfo noteInfo)
    {
        return toAjax(noteInfoService.insertNoteInfo(noteInfo));
    }

    /**
     * 修改文件夹
     */
    @PreAuthorize("@ss.hasPermi('note:noteInfo:edit')")
    @Log(title = "文件夹", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NoteInfo noteInfo)
    {
        return toAjax(noteInfoService.updateNoteInfo(noteInfo));
    }

    /**
     * 删除文件夹
     */
    @PreAuthorize("@ss.hasPermi('note:noteInfo:remove')")
    @Log(title = "文件夹", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(noteInfoService.deleteNoteInfoByIds(ids));
    }
}
