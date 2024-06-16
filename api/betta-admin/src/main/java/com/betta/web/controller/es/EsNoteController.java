package com.betta.web.controller.es;


import com.betta.common.annotation.Log;
import com.betta.common.core.controller.BaseController;
import com.betta.common.core.domain.AjaxResult;
import com.betta.common.core.page.TableDataInfo;
import com.betta.common.enums.BusinessType;
import com.betta.common.utils.SecurityUtils;
import com.betta.common.utils.StringUtils;
import com.betta.es.service.IEsNoteService;
import com.betta.note.domain.NoteInfo;
import com.betta.note.domain.NoteVo;
import com.betta.note.service.INoteInfoService;
import org.apache.ibatis.annotations.Param;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.List;

import static com.betta.common.utils.PageUtils.startPage;

@RestController
@RequestMapping("/es/note")
public class EsNoteController extends BaseController{

    @Autowired
    private IEsNoteService  esNoteService;

    @Autowired
    private INoteInfoService noteInfoService;

    @PreAuthorize("@ss.hasPermi('note:noteInfo:add')")
    @Log(title = "es", businessType = BusinessType.INSERT)
    @PostMapping("index")
    public AjaxResult addIndex(String userName) throws IOException {
        esNoteService.createIndex(userName);
        return AjaxResult.success();
    }



    /**
     * 分页添加doc
     * @param noteInfo
     * @param userName
     * @return
     */
    @PreAuthorize("@ss.hasPermi('note:noteInfo:add')")
    @PostMapping()
    public AjaxResult pageInsertDoc(NoteInfo noteInfo,@Param("userName") String userName) {
        startPage();
        List<NoteVo> list = noteInfoService.selectNoteInfoDetailList(noteInfo);
        TableDataInfo dataTable = getDataTable(list);
        esNoteService.insertPageNoteDoc(dataTable.getRows(),userName);
        return AjaxResult.success();
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
