package com.betta.eng.service;

import java.util.List;
import com.betta.eng.domain.PlayList;

/**
 * 播放列表Service接口
 * 
 * @author chenlt
 * @date 2024-06-25
 */
public interface IPlayListService 
{
    /**
     * 查询播放列表
     * 
     * @param id 播放列表主键
     * @return 播放列表
     */
    public PlayList selectPlayListById(Long id);

    /**
     * 查询播放列表列表
     * 
     * @param playList 播放列表
     * @return 播放列表集合
     */
    public List<PlayList> selectPlayListList(PlayList playList);

    /**
     * 新增播放列表
     * 
     * @param playList 播放列表
     * @return 结果
     */
    public int insertPlayList(PlayList playList);

    /**
     * 修改播放列表
     * 
     * @param playList 播放列表
     * @return 结果
     */
    public int updatePlayList(PlayList playList);

    /**
     * 批量删除播放列表
     * 
     * @param ids 需要删除的播放列表主键集合
     * @return 结果
     */
    public int deletePlayListByIds(Long[] ids);

    /**
     * 删除播放列表信息
     * 
     * @param id 播放列表主键
     * @return 结果
     */
    public int deletePlayListById(Long id);
}
