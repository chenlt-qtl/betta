package com.betta.eng.utils;

import com.betta.eng.domain.EngSentence;
import org.junit.Assert;
import org.junit.Test;

public class EngUtilsTest {

    @Test
    public void genMp3Time() {
        EngSentence sentence = new EngSentence();
        sentence.setContent("[00:07.50]Hey, glad you're here.");
        EngUtils.genMp3Time(sentence);
        Assert.assertEquals("7,5",sentence.getMp3Time());

        sentence.setContent("[05:07.50]Hey, glad you're here.");
        sentence.setMp3Time("");
        EngUtils.genMp3Time(sentence);
        Assert.assertEquals("307,5",sentence.getMp3Time());
    }

    @Test
    public void transMp3Time(){
        String startStr = "03:05.40,5";
        String mp3Time = EngUtils.transMp3Time(startStr);
        Assert.assertEquals("185,5",mp3Time);

    }
}