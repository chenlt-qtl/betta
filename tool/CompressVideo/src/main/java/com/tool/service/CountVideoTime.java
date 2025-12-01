package com.tool.service;

import ws.schild.jave.MultimediaObject;
import ws.schild.jave.info.AudioInfo;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public class CountVideoTime {

    public void countVideoTime(String path) {
        AtomicInteger time = new AtomicInteger(0);
        //根据文件路径统计里面所有视频的时长
        File dir = new File(path);
        countVideoTime(dir,time);
        int timeInt = time.intValue();
        //如果超过一个小时，把小时部分也输出来
        if (timeInt > 3600) {
            System.out.println("共有：" + time.intValue() / 3600 + "小时" + (timeInt % 3600) / 60 + "分钟的视频");
        }else {
            System.out.println("共有：" + time.intValue() / 60 + "分钟的视频");
        }
    }

    private void countVideoTime(File file, AtomicInteger time) {
        if(file.isDirectory()) {
            File[] files = file.listFiles();
            if (files.length > 0) {
                for (File source : files) {
                    countVideoTime(source, time);
                }
            }
        }else {
            try {
                MultimediaObject object = new MultimediaObject(file);
                AudioInfo audioInfo = object.getInfo().getAudio();
                // 根据视频大小来判断是否需要进行压缩,
                int maxSize = 5;
                // 获取视频文件大小,单位MB
                double videoSizeMB = Math.ceil(file.length() / 1048576);
                // 获取视频文件时长,单位秒
                int second = (int) object.getInfo().getDuration() / 1000;
                int oldValue = time.intValue();
                time.compareAndSet(oldValue, oldValue + second);
            } catch (Exception e) {
                System.out.println("出错了：" + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}
