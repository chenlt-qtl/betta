package com.betta.eng.service.impl;

import java.util.List;
import com.betta.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betta.eng.mapper.PlayListMapper;
import com.betta.eng.domain.PlayList;
import com.betta.eng.service.IPlayListService;

/**
 * 播放列表Service业务层处理
 * 
 * @author chenlt
 * @date 2024-06-25
 */
@Service
public class PlayListServiceImpl implements IPlayListService 
{
    @Autowired
    private PlayListMapper playListMapper;

    /**
     * 查询播放列表
     * 
     * @param id 播放列表主键
     * @return 播放列表
     */
    @Override
    public PlayList selectPlayListById(Long id)
    {
        return playListMapper.selectPlayListById(id);
    }

    /**
     * 查询播放列表列表
     * 
     * @param playList 播放列表
     * @return 播放列表
     */
    @Override
    public List<PlayList> selectPlayListList(PlayList playList)
    {
        return playListMapper.selectPlayListList(playList);
    }

    /**
     * 新增播放列表
     * 
     * @param playList 播放列表
     * @return 结果
     */
    @Override
    public int insertPlayList(PlayList playList)
    {
        playList.setCreateTime(DateUtils.getNowDate());
        return playListMapper.insertPlayList(playList);
    }

    /**
     * 修改播放列表
     * 
     * @param playList 播放列表
     * @return 结果
     */
    @Override
    public int updatePlayList(PlayList playList)
    {
        playList.setUpdateTime(DateUtils.getNowDate());
        return playListMapper.updatePlayList(playList);
    }

    /**
     * 批量删除播放列表
     * 
     * @param ids 需要删除的播放列表主键
     * @return 结果
     */
    @Override
    public int deletePlayListByIds(Long[] ids)
    {
        return playListMapper.deletePlayListByIds(ids);
    }

    /**
     * 删除播放列表信息
     * 
     * @param id 播放列表主键
     * @return 结果
     */
    @Override
    public int deletePlayListById(Long id)
    {
        return playListMapper.deletePlayListById(id);
    }
}
