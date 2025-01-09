package betta.module.importArticle.service;

import betta.domain.AjaxResult;
import betta.domain.BatchAddSentences;
import betta.service.RestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.regex.Pattern;

@Slf4j
@Component
public class SaveSentence {

    @Autowired
    private RestService restService;

    private static final Pattern PATTERN = Pattern.compile("(\\[[a-zA-Z:]+\\])|(字幕转换助手|tvsubtitles\\.net)");
    public void save(long articleId, File file) {
        StringBuilder sentenceStr = new StringBuilder();
        try (
                BufferedReader is = new BufferedReader(new FileReader(file));
        ) {
            String line;
            while (null!=(line = is.readLine())){
                if(!PATTERN.matcher(line).find()){
                    sentenceStr.append(line+"\n");
                }
            }
            log.info("句子文件读取完成");
            BatchAddSentences param = new BatchAddSentences();
            param.setArticleId(articleId);
            param.setSentenceStr(sentenceStr.toString());
            AjaxResult ajaxResult = restService.post("/eng/sentence/batch", param);
            if(ajaxResult.isSuccess()){
                log.info("句子添加成功");
            }
        } catch (Exception e) {
            log.error("读取句子文件出错，msg={}",e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
