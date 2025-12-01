
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
import java.math.BigDecimal;

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
            // 根据视频大小来判断是否需要进行压缩,
            int maxSize = 5;
            // 获取视频文件大小,单位MB
            double videoSizeMB = Math.ceil(source.length() / 1048576);
            // 获取视频文件时长,单位秒
            int second = (int) object.getInfo().getDuration() / 1000;

            //计算总时长
            int oldSecond = App.second.intValue();
            App.second.compareAndExchange(oldSecond, oldSecond + second);

            System.out.println(prefix + "视频大小：" + videoSizeMB + ",时长：" + second);
            // 视频每秒平均 MB
            BigDecimal meanSize = new BigDecimal(String.format("%.4f", videoSizeMB / second));
            System.out.println(prefix + "开始压缩视频了--> 视频每秒平均 " + meanSize + " MB ");

            long time = System.currentTimeMillis();
            // TODO 视频属性设置
//                 int maxBitRate = 128000;
            int maxBitRate = 66000;
            int maxSamplingRate = 44100;
            int bitRate = 147000;
            int maxFrameRate = 5;
            int maxWidth = 1280;

            AudioAttributes audio = new AudioAttributes();
            // 设置通用编码格式10                   audio.setCodec("aac");
            // 设置最大值：比特率越高，清晰度/音质越好
            // 设置音频比特率,单位:b (比特率越高，清晰度/音质越好，当然文件也就越大 128000 = 182kb)
            System.out.println(prefix + "原始音频比特率：" + audioInfo.getBitRate() + "--" + maxBitRate);
            if (audioInfo.getBitRate() > maxBitRate) {
                audio.setBitRate(maxBitRate);
            }

            // 设置重新编码的音频流中使用的声道数（1 =单声道，2 = 双声道（立体声））。如果未设置任何声道值，则编码器将选择默认值 0。
            audio.setChannels(audioInfo.getChannels());
            // 采样率越高声音的还原度越好，文件越大
            // 设置音频采样率，单位：赫兹 hz
            // 设置编码时候的音量值，未设置为0,如果256，则音量值不会改变
            // audio.setVolume(256);
            System.out.println(prefix + "原始音频采样率：" + audioInfo.getSamplingRate() + "---" + maxSamplingRate);
            if (audioInfo.getSamplingRate() > maxSamplingRate) {
                audio.setSamplingRate(maxSamplingRate);
            }

            // TODO 视频编码属性配置
            ws.schild.jave.info.VideoInfo videoInfo = object.getInfo().getVideo();
            VideoAttributes video = new VideoAttributes();
            video.setCodec("h264");
            //设置音频比特率,单位:b (比特率越高，清晰度/音质越好，当然文件也就越大 800000 = 800kb)
            System.out.println(prefix + "原始音频比特率：" + videoInfo.getBitRate() + "---" + bitRate);
            if (videoInfo.getBitRate() > bitRate) {
                video.setBitRate(bitRate);
            }

            // 视频帧率：15 f / s  帧率越低，效果越差
            // 设置视频帧率（帧率越低，视频会出现断层，越高让人感觉越连续），视频帧率（Frame rate）是用于测量显示帧数的量度。所谓的测量单位为每秒显示帧数(Frames per Second，简：FPS）或“赫兹”（Hz）。
            System.out.println(prefix + "原始视频帧率：" + videoInfo.getFrameRate() + "---" + maxFrameRate);
            if (videoInfo.getFrameRate() > maxFrameRate) {
                video.setFrameRate(maxFrameRate);
            }

            // 限制视频宽高
            int width = videoInfo.getSize().getWidth();
            int height = videoInfo.getSize().getHeight();
            System.out.println(prefix + "原始视频宽度：" + width + "---" + maxWidth);
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

            System.out.println(prefix +"压缩前视频大小：" + videoSizeMB + ",压缩后视频大小：" + target.length() / 1048576 + ",总耗时：" + (double) (System.currentTimeMillis() - time) / 1000 + "秒");
            App.SUCCESS_LIST.add(target.getAbsolutePath());
            return target;

        } catch (Exception e) {
            App.FAIL_LIST.add(source.getAbsolutePath());
            //将原视频复制到新文件夹中
            e.printStackTrace();
        }
        return source;
    }
}