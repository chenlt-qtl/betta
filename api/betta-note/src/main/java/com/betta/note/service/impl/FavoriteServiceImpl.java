package com.betta.note.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.betta.common.utils.DateUtils;
import com.betta.common.utils.SecurityUtils;
import com.betta.common.utils.StringUtils;
import com.betta.note.domain.NoteInfo;
import com.betta.note.service.INoteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betta.note.mapper.FavoriteMapper;
import com.betta.note.domain.Favorite;
import com.betta.note.service.IFavoriteService;

/**
 * 收藏Service业务层处理
 * 
 * @author chenlt
 * @date 2024-06-12
 */
@Service
public class FavoriteServiceImpl implements IFavoriteService 
{
    @Autowired
    private FavoriteMapper favoriteMapper;

    @Autowired
    private INoteInfoService noteInfoService;

    /**
     * 查询收藏
     * 
     * @param id 收藏主键
     * @return 收藏
     */
    @Override
    public Favorite selectFavoriteById(Long id)
    {
        return favoriteMapper.selectFavoriteById(id);
    }

    /**
     * 查询收藏列表
     * 
     * @param favorite 收藏
     * @return 收藏
     */
    @Override
    public List<Favorite> selectFavoriteList(Favorite favorite)
    {
        return favoriteMapper.selectFavoriteList(favorite);
    }

    /**
     * 新增收藏
     * 
     * @param favorite 收藏
     * @return 结果
     */
    @Override
    public int insertFavorite(Favorite favorite)
    {
        favorite.setCreateTime(DateUtils.getNowDate());
        return favoriteMapper.insertFavorite(favorite);
    }

    /**
     * 修改收藏
     * 
     * @param favorite 收藏
     * @return 结果
     */
    @Override
    public int updateFavorite(Favorite favorite)
    {
        favorite.setUpdateTime(DateUtils.getNowDate());
        return favoriteMapper.updateFavorite(favorite);
    }

    /**
     * 批量删除收藏
     * 
     * @param ids 需要删除的收藏主键
     * @return 结果
     */
    @Override
    public int deleteFavoriteByIds(Long[] ids)
    {
        return favoriteMapper.deleteFavoriteByIds(ids);
    }

    /**
     * 删除收藏信息
     * 
     * @param id 收藏主键
     * @return 结果
     */
    @Override
    public int deleteFavoriteById(Long id)
    {
        return favoriteMapper.deleteFavoriteById(id);
    }

    /**
     * 获取收藏的笔记信息
     * @return
     */
    @Override
    public List<NoteInfo> selectFavNoteList() {
        List<NoteInfo> result = new ArrayList<>();
        Favorite favorite = new Favorite();
        favorite.setCreateBy(SecurityUtils.getUsername());
        List<Favorite> list = selectFavoriteList(favorite);
        if (!list.isEmpty()) {
            favorite = list.get(0);
            if(StringUtils.hasText(favorite.getNoteIds())) {
                String[] noteIds = favorite.getNoteIds().split(",");
                result = noteInfoService.selectNoteInfoByIds(noteIds);
            }
        }
        return result;
    }
}
