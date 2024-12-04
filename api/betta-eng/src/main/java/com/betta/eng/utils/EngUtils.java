package com.betta.eng.utils;

import cn.hutool.core.util.StrUtil;
import com.betta.eng.domain.EngSentence;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EngUtils {

    private EngUtils() {
    }

    /**
     * 生成MP3TIME字段
     * @param sentence
     */
    public static void genMp3Time(EngSentence sentence) {
        //如果时间是空的 从content中提取时间

        String mp3Time = sentence.getMp3Time();
        if (StrUtil.isBlank(mp3Time)) {
            String content = sentence.getContent();

            //把[18:10.33]...中的时间解析出来
            Pattern pattern = Pattern.compile("^\\[(\\d+:\\d+)");
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                String group = matcher.group(1);
                mp3Time = transMp3Time(group+ "," + (content.length() < 60 ? 5 : 8));
            }
        }else{
            mp3Time = transMp3Time(mp3Time);
        }
        sentence.setMp3Time(mp3Time);
    }

    /**
     * 统一mp3time格式为  开始秒数,时长，倍速
     * @param mp3Time
     * @return
     */
    public static String transMp3Time(String mp3Time) {
        if (StrUtil.isNotBlank(mp3Time)) {
            String[] timeArr = mp3Time.split(",");
            String startStr = timeArr[0];

            Pattern pattern = Pattern.compile("^(\\d+:\\d+)");
            Matcher matcher = pattern.matcher(startStr);
            if (matcher.find()) {
                String group = matcher.group(1);
                String[] times = group.split(":");
                int startTime = Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
                return startTime + "," + timeArr[1];
            }
        }
        return mp3Time;
    }

}
