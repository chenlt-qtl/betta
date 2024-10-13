package com.betta.eng.utils.dict;

import com.betta.eng.domain.EngWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DictUtils {

    @Autowired
    private IcibaUtils icibaUtils;

    @Autowired
    private YouDaoUtils youDaoUtils;


    public EngWord getWord(String wordName){
        //先查爱词霸再查有道
        EngWord word = null;
        try {
            word = icibaUtils.getWord(wordName);
        } catch (Exception e) {
            word = youDaoUtils.getWord(wordName);
        }
        return word;
    }
}
