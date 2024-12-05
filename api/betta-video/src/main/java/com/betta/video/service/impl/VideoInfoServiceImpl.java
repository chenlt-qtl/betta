package com.betta.video.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.betta.common.constant.UserConstants;
import com.betta.common.exception.ApiException;
import com.betta.common.utils.DateUtils;
import com.betta.common.utils.SecurityUtils;
import com.betta.video.domain.VideoChildDto;
import com.betta.video.domain.VideoListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betta.video.mapper.VideoInfoMapper;
import com.betta.video.domain.VideoInfo;
import com.betta.video.service.IVideoInfoService;

import static com.betta.common.constant.UserConstants.YES;

/**
 * 视频信息Service业务层处理
 *
 * @author ruoyi
 * @date 2024-12-04
 */
@Service
public class VideoInfoServiceImpl extends ServiceImpl<VideoInfoMapper, VideoInfo> implements IVideoInfoService {
    @Autowired
    private VideoInfoMapper videoInfoMapper;

    /**
     * 查询视频信息
     *
     * @param id 视频信息主键
     * @return 视频信息
     */
    @Override
    public VideoInfo selectVideoInfoById(Long id) {
        return videoInfoMapper.selectVideoInfoById(id);
    }

    /**
     * 查询视频信息列表
     *
     * @param videoInfo 视频信息
     * @return 视频信息
     */
    @Override
    public List<VideoListVo> selectVideoInfoList(VideoInfo videoInfo) {
        QueryWrapper<VideoListVo> wrapper = new QueryWrapper<>();
        wrapper.eq(videoInfo.getPid() != null, "a.pid", videoInfo.getPid());
        wrapper.like(StrUtil.isNotBlank(videoInfo.getTitle()), "a.title", videoInfo.getTitle());
        wrapper.eq("a.create_by", SecurityUtils.getUsername());
        return videoInfoMapper.selectVideoInfoList(wrapper);
    }

    /**
     * 新增视频信息
     *
     * @param videoInfo 视频信息
     * @return 结果
     */
    @Override
    public int insertVideoInfo(VideoInfo videoInfo) {
        if (videoInfo.getId() != null && (long) videoInfo.getId() == videoInfo.getPid()) {
            throw new ApiException("上级不是能自己");
        }
        return videoInfoMapper.insert(videoInfo);
    }

    /**
     * 修改视频信息
     *
     * @param videoInfo 视频信息
     * @return 结果
     */
    @Override
    public int updateVideoInfo(VideoInfo videoInfo) {
        videoInfo.setUpdateTime(DateUtils.getNowDate());
        return videoInfoMapper.updateById(videoInfo);
    }

    /**
     * 批量删除视频信息
     *
     * @param ids 需要删除的视频信息主键
     * @return 结果
     */
    @Override
    public int deleteVideoInfoByIds(Long[] ids) {
        //檢查是否有下級
        for (int i = 0; i < ids.length; i++) {
            List<VideoInfo> videoInfos = listChildren(ids[i]);
            if (!videoInfos.isEmpty()) {
                throw new ApiException("请先删除子视频");
            }
        }

        return videoInfoMapper.deleteVideoInfoByIds(ids);
    }

    /**
     * 获取子视频
     *
     * @param pid
     * @return
     */
    @Override
    public List<VideoInfo> listChildren(Long pid) {
        LambdaQueryWrapper<VideoInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(VideoInfo::getPid, pid);
        return videoInfoMapper.selectList(wrapper);
    }

    @Override
    public List<VideoInfo> selectVideoInfoBrief(char isLeaf) {
        LambdaQueryWrapper<VideoInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(VideoInfo::getPid, VideoInfo::getTitle, VideoInfo::getId);
        wrapper.eq(!CharUtil.isBlankChar(isLeaf),VideoInfo::getIsLeaf,isLeaf);
        wrapper.eq(VideoInfo::getCreateBy,SecurityUtils.getUsername());
        return videoInfoMapper.selectList(wrapper);
    }

    @Override
    public boolean addChildren(VideoChildDto dto) {
        if (dto.getPid() == null || StrUtil.isBlank(dto.getText())) {
            return false;
        }
        //1. 检查父视频
        VideoInfo parent = videoInfoMapper.selectVideoInfoById(dto.getPid());
        if (parent == null) {
            throw new ApiException("父视频不存在");
        }
        if (StrUtil.isBlank(parent.getUrl())) {
            throw new ApiException("父视频没有地址");
        }

        //2.查询已有的子视频个数
        QueryWrapper<VideoInfo> wrapper = new QueryWrapper<>();
        wrapper.select("count(*) as count");
        wrapper.eq("pid", dto.getPid());
        Map<String, Object> map = getMap(wrapper);
        Long start = (Long) map.get("count");

        //3.组装子视频
        String[] texts = dto.getText().split("\n");
        List<VideoInfo> list = new ArrayList<>();
        for (int i = 0; i < texts.length; i++) {
            String title = texts[i];
            VideoInfo videoInfo = new VideoInfo();
            //提取时长
            Pattern pattern = Pattern.compile("^(\\d+:\\d+(?::\\d+)*)[,，]([\\w\\W]+)");
            Matcher matcher = pattern.matcher(title);
            if (matcher.find()) {
                videoInfo.setDuration(matcher.group(1));
                title = matcher.group(2);
            }

            videoInfo.setUrl(parent.getUrl() + "&p=" + ++start);
            videoInfo.setTitle(title);
            videoInfo.setPid(dto.getPid());
            videoInfo.setIsLeaf(UserConstants.YES);
            list.add(videoInfo);
        }

        //4.批量保存
        return saveBatch(list);
    }
}
