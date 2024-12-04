package com.betta.eng.domain.vo;

import com.betta.eng.domain.EngIcibaSentence;
import com.betta.eng.domain.EngSentence;
import com.betta.eng.domain.EngWord;
import lombok.Data;

import java.util.List;

/**
 * 单词对象 eng_word
 * 
 * @author ruoyi
 * @date 2024-06-03
 */
@Data
public class EngWordVo extends EngWord
{
    private static final long serialVersionUID = 1L;


    private Long relId;

    /**
     * 爱词霸例句
     */
    private List<EngIcibaSentence> icibaSentenceList;

    /**
     * 自定义例句
     */
    private List<EngSentence> sentenceList;

}
