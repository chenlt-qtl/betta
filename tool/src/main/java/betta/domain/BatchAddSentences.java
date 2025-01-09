package betta.domain;

import lombok.Data;

@Data
public class BatchAddSentences {
    private String sentenceStr;
    private Long articleId;

}
