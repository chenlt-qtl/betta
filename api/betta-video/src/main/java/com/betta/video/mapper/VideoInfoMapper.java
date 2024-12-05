package com.betta.video.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.betta.video.domain.VideoInfo;
import com.betta.video.domain.VideoListVo;
import org.apache.ibatis.annotations.Param;

/**
 * 视频信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-12-04
 */
public interface VideoInfoMapper extends BaseMapper<VideoInfo>
{
    /**
     * 查询视频信息
     * 
     * @param id 视频信息主键
     * @return 视频信息
     */
    public VideoInfo selectVideoInfoById(Long id);

    /**
     * 查询视频信息列表
     * 
     * @return 视频信息集合
     */
    public List<VideoListVo> selectVideoInfoList(@Param(Constants.WRAPPER) QueryWrapper<VideoListVo> wrapper);


    /**
     * 删除视频信息
     * 
     * @param id 视频信息主键
     * @return 结果
     */
    public int deleteVideoInfoById(Long id);

    /**
     * 批量删除视频信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteVideoInfoByIds(Long[] ids);
}
