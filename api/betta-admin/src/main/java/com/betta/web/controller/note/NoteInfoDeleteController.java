package com.betta.web.controller.note;

import com.betta.common.annotation.Log;
import com.betta.common.core.controller.BaseController;
import com.betta.common.core.domain.AjaxResult;
import com.betta.common.core.page.TableDataInfo;
import com.betta.common.enums.BusinessType;
import com.betta.common.utils.poi.ExcelUtil;
import com.betta.note.domain.NoteInfoDelete;
import com.betta.note.service.INoteInfoDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 删除历史Controller
 * 
 * @author ruoyi
 * @date 2024-06-06
 */
@RestController
@RequestMapping("/note/noteDelete")
public class NoteInfoDeleteController extends BaseController
{
    @Autowired
    private INoteInfoDeleteService noteInfoDeleteService;

    /**
     * 查询删除历史列表
     */
    @PreAuthorize("@ss.hasPermi('note:noteDelete:list')")
    @GetMapping("/list")
    public TableDataInfo list(NoteInfoDelete noteInfoDelete)
    {
        startPage();
        List<NoteInfoDelete> list = noteInfoDeleteService.selectNoteInfoDeleteList(noteInfoDelete);
        return getDataTable(list);
    }

    /**
     * 导出删除历史列表
     */
    @PreAuthorize("@ss.hasPermi('note:noteDelete:export')")
    @Log(title = "删除历史", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NoteInfoDelete noteInfoDelete)
    {
        List<NoteInfoDelete> list = noteInfoDeleteService.selectNoteInfoDeleteList(noteInfoDelete);
        ExcelUtil<NoteInfoDelete> util = new ExcelUtil<NoteInfoDelete>(NoteInfoDelete.class);
        util.exportExcel(response, list, "删除历史数据");
    }

    /**
     * 获取删除历史详细信息
     */
    @PreAuthorize("@ss.hasPermi('note:noteDelete:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(noteInfoDeleteService.selectNoteInfoDeleteById(id));
    }

    /**
     * 新增删除历史
     */
    @PreAuthorize("@ss.hasPermi('note:noteDelete:add')")
    @Log(title = "删除历史", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NoteInfoDelete noteInfoDelete)
    {
        return toAjax(noteInfoDeleteService.insertNoteInfoDelete(noteInfoDelete));
    }

    /**
     * 修改删除历史
     */
    @PreAuthorize("@ss.hasPermi('note:noteDelete:edit')")
    @Log(title = "删除历史", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NoteInfoDelete noteInfoDelete)
    {
        return toAjax(noteInfoDeleteService.updateNoteInfoDelete(noteInfoDelete));
    }

    /**
     * 删除删除历史
     */
    @PreAuthorize("@ss.hasPermi('note:noteDelete:remove')")
    @Log(title = "删除历史", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(noteInfoDeleteService.deleteNoteInfoDeleteByIds(ids));
    }
}
