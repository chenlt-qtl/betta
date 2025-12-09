
package com.tool.util;

import com.tool.App;
import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;
import ws.schild.jave.info.AudioInfo;
import ws.schild.jave.info.VideoSize;

import java.io.File;
import java.util.Date;

/**
 * 视频压缩工具类
 *
 * @Author zwm
 * @Date 2025-01-06
 */

public class CompressionVideoUtil {

    // 视频压缩
    public static File compressionVideo(File source, File target, String prefix) {

        if (source == null) {
            return null;
        }
        try {
            MultimediaObject object = new MultimediaObject(source);
            AudioInfo audioInfo = object.getInfo().getAudio();
            // 获取视频文件大小,单位MB
            double videoSizeMB = source.length() / 1048576.0;
            // 获取视频文件时长,单位秒
            int second = (int) object.getInfo().getDuration() / 1000;

            //计算总时长 - 使用更高效的原子操作
            App.second.addAndGet(second);

            // 优化：只输出关键信息，减少日志
            System.out.println(new Date() + "开始压缩: " + prefix + source.getName() + " (大小:" + String.format("%.1f", videoSizeMB) + "MB, 时长:" + second + "s)");

            long time = System.currentTimeMillis();
            
            // 优化编码参数，提高压缩效率
            int maxBitRate = 64000;  // 降低音频比特率
            int maxSamplingRate = 22050;  // 降低采样率
            int bitRate = 128000;  // 降低视频比特率
            int maxFrameRate = 8;  // 适当提高帧率，平衡质量和速度
            int maxWidth = 1920;

            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("aac");
            
            if (audioInfo != null) {
                if (audioInfo.getBitRate() > maxBitRate) {
                    audio.setBitRate(maxBitRate);
                }
                audio.setChannels(audioInfo.getChannels());
                if (audioInfo.getSamplingRate() > maxSamplingRate) {
                    audio.setSamplingRate(maxSamplingRate);
                }
            } else {
                // 如果没有音频信息，设置默认值
                audio.setBitRate(maxBitRate);
                audio.setChannels(2);
                audio.setSamplingRate(maxSamplingRate);
            }

            // 视频编码属性配置
            ws.schild.jave.info.VideoInfo videoInfo = object.getInfo().getVideo();
            VideoAttributes video = new VideoAttributes();
            video.setCodec("h264");
            
            if (videoInfo != null) {
                if (videoInfo.getBitRate() > bitRate) {
                    video.setBitRate(bitRate);
                }
            }

            // 视频帧率：15 f / s  帧率越低，效果越差
            if (videoInfo.getFrameRate() > maxFrameRate) {
                video.setFrameRate(maxFrameRate);
            }

            // 限制视频宽高
            int width = videoInfo.getSize().getWidth();
            int height = videoInfo.getSize().getHeight();

            // 设置视频帧率（帧率越低，视频会出现断层，越高让人感觉越连续），视频帧率（Frame rate）是用于测量显示帧数的量度。所谓的测量单位为每秒显示帧数(Frames per Second，简：FPS）或“赫兹”（Hz）。
            System.out.println(prefix + "原始视频帧率：" + videoInfo.getFrameRate() + "---" + maxFrameRate+ "，原始视频宽度：" + width + "---" + maxWidth);
            if (width > maxWidth) {
                // height 不能整除2会报错 Exit code of ffmpeg encoding run is 1
                float rat = (float) width / maxWidth;
                int newHeight = (int) (height / rat) % 2 == 1 ? (int) (height / rat) + 1 : (int) (height / rat);
                video.setSize(new VideoSize(maxWidth, newHeight));
            }

            EncodingAttributes attr = new EncodingAttributes();
            attr.setAudioAttributes(audio);
            attr.setVideoAttributes(video);

            Encoder encoder = new Encoder();
            encoder.encode(new MultimediaObject(source), target, attr);

            double compressedSize = target.length() / 1048576.0;
            double compressionRatio = (1 - compressedSize / videoSizeMB) * 100;
            System.out.println(prefix + "压缩完成: " + String.format("%.1f", videoSizeMB) + "MB -> " + 
                             String.format("%.1f", compressedSize) + "MB (压缩率:" + 
                             String.format("%.1f", compressionRatio) + "%, 耗时:" + 
                             String.format("%.1f", (System.currentTimeMillis() - time) / 1000.0) + "s)");
            App.SUCCESS_LIST.add(target.getAbsolutePath());
            return target;

        } catch (Exception e) {
            System.err.println(prefix + "压缩失败: " + e.getMessage());
            App.FAIL_LIST.add(source.getAbsolutePath());
            e.printStackTrace();
        }
        return source;
    }
}