package com.betta.eng.utils.dict;

import com.betta.common.constant.Constants;
import com.betta.eng.domain.vo.EngWordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DictUtils {

    @Autowired
    private IcibaUtils icibaUtils;

    @Autowired
    private YouDaoUtils youDaoUtils;


    public EngWordVo getWord(String wordName) {
        //先查爱词霸再查有道
        EngWordVo word = null;
        try {
            word = icibaUtils.getWord(wordName);
        } catch (Exception e) {
            word = youDaoUtils.getWord(wordName);
        }
        return word;
    }

    /**
     * 生成单词Mp3的相对路径
     *
     * @param wordName
     * @return
     */
    public static String getWordMp3RelativePath(String wordName) {
        return Constants.WORD_PATH + "/" + wordName.substring(0, 1) + "/" + wordName + ".mp3";
    }
}
