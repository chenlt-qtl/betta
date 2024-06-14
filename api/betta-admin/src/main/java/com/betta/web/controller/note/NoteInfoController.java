package com.betta.web.controller.note;

import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;

import com.betta.common.annotation.CreateByScope;
import com.betta.common.core.domain.entity.SysDept;
import com.betta.common.exception.ServiceException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
public class NoteInfoController extends BaseController {
    @Autowired
    private INoteInfoService noteInfoService;

    /**
     * 获取部门树列表
     */
    @PreAuthorize("@ss.hasPermi('note:noteInfo:list')")
    @GetMapping("/tree")
    public AjaxResult noteTree(NoteInfo noteInfo) {
        return success(noteInfoService.selectNoteTreeList(noteInfo));
    }

    /**
     * 查询文件夹列表
     */
    @PreAuthorize("@ss.hasPermi('note:noteInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(NoteInfo noteInfo) {
        startPage();
        List<NoteInfo> list = noteInfoService.selectNoteInfoList(noteInfo);
        return getDataTable(list);
    }

    /**
     * 查询文件夹列表
     */
    @PreAuthorize("@ss.hasPermi('note:noteInfo:list')")
    @GetMapping("/last")
    public AjaxResult listLast() {
        List<NoteInfo> list = noteInfoService.selectLast(20);
        return AjaxResult.success(list);
    }

    /**
     * 导出文件夹列表
     */
    @PreAuthorize("@ss.hasPermi('note:noteInfo:export')")
    @Log(title = "文件夹", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NoteInfo noteInfo) {
        List<NoteInfo> list = noteInfoService.selectNoteInfoList(noteInfo);
        ExcelUtil<NoteInfo> util = new ExcelUtil<NoteInfo>(NoteInfo.class);
        util.exportExcel(response, list, "文件夹数据");
    }

    /**
     * 获取文件夹详细信息
     */
    @PreAuthorize("@ss.hasPermi('note:noteInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        NoteInfo noteInfo = noteInfoService.selectNoteInfoById(id);
        if (Objects.isNull(noteInfo)) {
            throw new ServiceException(String.format("ID为%d的笔记不存在", id));
        }
        return success(noteInfo);
    }

    /**
     * 新增文件夹
     */
    @PreAuthorize("@ss.hasPermi('note:noteInfo:add')")
    @Log(title = "文件夹", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NoteInfo noteInfo) {
        return AjaxResult.success(noteInfoService.insertNoteInfo(noteInfo));
    }

    /**
     * 修改文件夹
     */
    @PreAuthorize("@ss.hasPermi('note:noteInfo:edit')")
    @Log(title = "文件夹", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NoteInfo noteInfo) {
        return toAjax(noteInfoService.updateNoteInfo(noteInfo));
    }

    /**
     * 修改标题
     */
    @PreAuthorize("@ss.hasPermi('note:noteInfo:edit')")
    @Log(title = "文件夹", businessType = BusinessType.UPDATE)
    @PutMapping("/{id}")
    public AjaxResult edit(@PathVariable Long id, @RequestBody NoteInfo noteInfo) {
        NoteInfo old = noteInfoService.selectNoteInfoById(id);
        if (Objects.isNull(old)) {
            throw new ServiceException(String.format("ID为%d的笔记不存在", id));
        }
        old.setName(noteInfo.getName());
        return toAjax(noteInfoService.updateNoteInfo(old));
    }

    /**
     * 更改父节点
     *
     * @return
     */
    @PutMapping(value = "/parent")
    public AjaxResult updateParent(@RequestParam String ids, @RequestParam Long parentId) {
        noteInfoService.updateParent(ids.split(","), parentId);
        return AjaxResult.success();
    }


    /**
     * 删除文件夹
     */
    @PreAuthorize("@ss.hasPermi('note:noteInfo:remove')")
    @Log(title = "文件夹", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(noteInfoService.deleteNoteInfoByIds(ids));
    }
}
