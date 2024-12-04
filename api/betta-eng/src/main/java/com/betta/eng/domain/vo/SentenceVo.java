package com.betta.eng.domain.vo;

import com.betta.eng.domain.EngSentence;
import lombok.Data;

/**
 * 文章句子VO对象
 * 
 * @author ruoyi
 * @date 2024-06-02
 */
@Data
public class SentenceVo extends EngSentence
{
    private static final long serialVersionUID = 1L;

    private String articleName;

    private String groupName;

    private Long groupId;
}
