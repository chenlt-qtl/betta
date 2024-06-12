package com.betta.note.service;

import java.util.List;
import com.betta.note.domain.Favorite;

/**
 * 收藏Service接口
 * 
 * @author chenlt
 * @date 2024-06-12
 */
public interface IFavoriteService 
{
    /**
     * 查询收藏
     * 
     * @param id 收藏主键
     * @return 收藏
     */
    public Favorite selectFavoriteById(Long id);

    /**
     * 查询收藏列表
     * 
     * @param favorite 收藏
     * @return 收藏集合
     */
    public List<Favorite> selectFavoriteList(Favorite favorite);

    /**
     * 新增收藏
     * 
     * @param favorite 收藏
     * @return 结果
     */
    public int insertFavorite(Favorite favorite);

    /**
     * 修改收藏
     * 
     * @param favorite 收藏
     * @return 结果
     */
    public int updateFavorite(Favorite favorite);

    /**
     * 批量删除收藏
     * 
     * @param ids 需要删除的收藏主键集合
     * @return 结果
     */
    public int deleteFavoriteByIds(Long[] ids);

    /**
     * 删除收藏信息
     * 
     * @param id 收藏主键
     * @return 结果
     */
    public int deleteFavoriteById(Long id);
}
