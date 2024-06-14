package com.betta.web.controller.note;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.betta.common.utils.SecurityUtils;
import com.betta.note.domain.NoteInfo;
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
import com.betta.note.domain.Favorite;
import com.betta.note.service.IFavoriteService;
import com.betta.common.utils.poi.ExcelUtil;
import com.betta.common.core.page.TableDataInfo;

/**
 * 收藏Controller
 *
 * @author chenlt
 * @date 2024-06-12
 */
@RestController
@RequestMapping("/note/favorite")
public class FavoriteController extends BaseController {
    @Autowired
    private IFavoriteService favoriteService;

    /**
     * 查询收藏笔记列表
     */
    @PreAuthorize("@ss.hasPermi('note:favorite:list')")
    @GetMapping("/list")
    public AjaxResult list() {
        List<NoteInfo> list = favoriteService.selectFavNoteList();
        return AjaxResult.success(list);
    }

    @PreAuthorize("@ss.hasPermi('note:favorite:query')")
    @GetMapping("/cur")
    public AjaxResult getCur() {
        Favorite favorite = new Favorite();
        favorite.setCreateBy(SecurityUtils.getUsername());
        List<Favorite> list = favoriteService.selectFavoriteList(favorite);
        if (!list.isEmpty()) {
            favorite = list.get(0);
        }
        return AjaxResult.success(favorite);
    }

    /**
     * 导出收藏列表
     */
    @PreAuthorize("@ss.hasPermi('note:favorite:export')")
    @Log(title = "收藏", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Favorite favorite) {
        List<Favorite> list = favoriteService.selectFavoriteList(favorite);
        ExcelUtil<Favorite> util = new ExcelUtil<Favorite>(Favorite.class);
        util.exportExcel(response, list, "收藏数据");
    }

    /**
     * 获取收藏详细信息
     */
    @PreAuthorize("@ss.hasPermi('note:favorite:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(favoriteService.selectFavoriteById(id));
    }

    /**
     * 新增收藏
     */
    @PreAuthorize("@ss.hasPermi('note:favorite:add')")
    @Log(title = "收藏", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Favorite favorite) {
        return toAjax(favoriteService.insertFavorite(favorite));
    }

    /**
     * 修改收藏
     */
    @PreAuthorize("@ss.hasPermi('note:favorite:edit')")
    @Log(title = "收藏", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Favorite favorite) {
        return toAjax(favoriteService.updateFavorite(favorite));
    }

    /**
     * 删除收藏
     */
    @PreAuthorize("@ss.hasPermi('note:favorite:remove')")
    @Log(title = "收藏", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(favoriteService.deleteFavoriteByIds(ids));
    }
}
