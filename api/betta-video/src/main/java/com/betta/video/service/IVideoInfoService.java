package com.betta.video.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.betta.video.domain.VideoChildDto;
import com.betta.video.domain.VideoInfo;
import com.betta.video.domain.VideoListVo;

/**
 * 视频信息Service接口
 * 
 * @author ruoyi
 * @date 2024-12-04
 */
public interface IVideoInfoService  extends IService<VideoInfo>
{

    /**
     * 查询视频信息列表
     * 
     * @param videoInfo 视频信息
     * @return 视频信息集合
     */
    public List<VideoListVo> selectVideoInfoList(VideoInfo videoInfo);

    /**
     * 新增视频信息
     * 
     * @param videoInfo 视频信息
     * @return 结果
     */
    public int insertVideoInfo(VideoInfo videoInfo);

    /**
     * 修改视频信息
     * 
     * @param videoInfo 视频信息
     * @return 结果
     */
    public int updateVideoInfo(VideoInfo videoInfo);

    /**
     * 批量删除视频信息
     * 
     * @param ids 需要删除的视频信息主键集合
     * @return 结果
     */
    public int deleteVideoInfoByIds(Long[] ids);


    List<VideoInfo> listChildren(Long pid);

    List<VideoInfo> selectVideoInfoBrief(char isLeaf);

    boolean addChildren(VideoChildDto videoInfo);
}
