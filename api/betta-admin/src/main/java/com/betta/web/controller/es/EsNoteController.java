package com.betta.web.controller.es;


import com.betta.common.annotation.Log;
import com.betta.common.core.controller.BaseController;
import com.betta.common.core.domain.AjaxResult;
import com.betta.common.enums.BusinessType;
import com.betta.common.utils.StringUtils;
import com.betta.es.service.IEsHistoryService;
import com.betta.es.service.IEsNoteService;
import com.betta.note.domain.NoteInfo;
import com.betta.note.domain.NoteVo;
import com.betta.note.service.INoteInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.List;

@RestController
@RequestMapping("/es/note")
public class EsNoteController extends BaseController{

    @Autowired
    private IEsNoteService  esNoteService;

    @Autowired
    private INoteInfoService noteInfoService;

    @Autowired
    private IEsHistoryService esHistoryService;

    @PreAuthorize("@ss.hasPermi('note:noteInfo:add')")
    @Log(title = "es", businessType = BusinessType.INSERT)
    @PostMapping("index")
    public AjaxResult addIndex(String userName) throws IOException {
        esNoteService.createIndex(userName);
        return AjaxResult.success();
    }

    @Log(title = "es", businessType = BusinessType.DELETE)
    @DeleteMapping("index/{userName}")
    public AjaxResult deleteIndex(@PathVariable String userName) throws IOException {
        esNoteService.deleteIndex(userName);
        return AjaxResult.success();
    }



    /**
     * 批量添加doc
     * @param userName
     * @return
     */
    @PreAuthorize("@ss.hasPermi('note:noteInfo:add')")
    @PostMapping()
    public AjaxResult batchInsertDoc(@Param("userName") String userName) {
        Long count = esNoteService.batchInsertDoc(userName);
        return AjaxResult.success(count);
    }

    @PreAuthorize("@ss.hasPermi('note:noteInfo:add')")
    @Log(title = "es", businessType = BusinessType.INSERT)
    @PostMapping("/{id}")
    public AjaxResult addDoc(@PathVariable Long id) throws IOException {
        esNoteService.insertNoteDoc(id);
        return AjaxResult.success();
    }

    @GetMapping
    public AjaxResult searchDoc(String key) throws IOException {
        if(!StringUtils.hasText(key)){
            throw new ServerException("请输入关键字");
        }
        List<NoteVo> list = esNoteService.search(key);
        return AjaxResult.success(list);
    }

}
