package com.betta.eng.utils.dict;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.betta.common.config.BettaConfig;
import com.betta.common.constant.Constants;
import com.betta.common.exception.ServiceException;
import com.betta.common.utils.file.FileUtils;
import com.betta.common.utils.http.HttpUtils;
import com.betta.eng.domain.EngWord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class YouDaoUtils implements IDictUtils {

    private static final String YOUDAO_URL = "http://dict.youdao.com/suggest";
    private static final String DICTIONARY_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/";

    @Override
    public EngWord getWord(String wordName) {

        EngWord word = null;
        try {
            word = getMeans(wordName);
            getMp3(word);
        } catch (Exception e) {
            throw new ServiceException("获取有道词典出错：" + wordName);
        }
        return word;
    }

    /**
     * 获取意思
     *
     * @return
     */
    private EngWord getMeans(String wordName) {

        String rspStr = HttpUtils.sendGet(YOUDAO_URL, "q=" + wordName + "&num=1&doctype=json", Constants.UTF8);
        JSONObject jsonObject = JSON.parseObject(rspStr);
        JSONObject data = (JSONObject) jsonObject.get("data");
        JSONArray entries = data.getJSONArray("entries");
        JSONObject obj = (JSONObject) entries.get(0);
        String explain = (String) obj.get("explain");

        EngWord word = new EngWord();
        word.setWordName(wordName);
        word.setAcceptation(explain);
        return word;
    }

    /**
     * 获取音频
     *
     * @return
     */
    private void getMp3(EngWord word) throws IOException {
        String rspStr = HttpUtils.sendGet(DICTIONARY_URL + word.getWordName(), null, Constants.UTF8);
        JSONArray objects = JSON.parseArray(rspStr);
        JSONObject object1 = (JSONObject) objects.get(0);
        JSONArray phonetics = object1.getJSONArray("phonetics");
        boolean hasUs = false;
        String temp_audio = "";
        for (int i = 0; i < phonetics.size(); i++) {
            JSONObject phObj = (JSONObject) phonetics.get(i);
            String ph = "";
            if (phObj.containsKey("text")) {
                ph = (String) phObj.get("text");
            }
            if (phObj.containsKey("audio")) {
                String audio = (String) phObj.get("audio");
                if (audio.matches("[\\d\\D]+\\-us.mp3")) {
                    hasUs = true;
                    word.setPhonetics(ph);
                    temp_audio = audio;
                } else if (audio.matches("[\\d\\D]+\\-uk.mp3")) {
                    {

                        if (hasUs == false) {
                            word.setPhonetics(ph);
                            temp_audio = audio;
                        }
                    }
                }


            }
        }
        //如果temp_audio不为空 保存到服务器  只保存一个MP3
        if (StringUtils.isNotBlank(temp_audio)) {
            String path = BettaConfig.getWordPath() + "/" + word.getWordName().substring(0, 1);
            String mp3Path = FileUtils.writeBytes(temp_audio, path, word.getWordName() + ".mp3");
            word.setPhMp3(mp3Path);
        }
    }

    public static void main(String[] args) {
        YouDaoUtils youDaoUtils = new YouDaoUtils();
        EngWord hello = youDaoUtils.getWord("hello");
        System.out.println(hello);
    }
}
