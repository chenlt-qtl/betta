package betta.module.importArticle.service.sentence;

import betta.domain.AjaxResult;
import betta.domain.BatchAddSentences;
import betta.service.RestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

@Slf4j
public abstract class SaveSentence {

    @Autowired
    private RestService restService;

    abstract Pattern getPattern();
    abstract String getCharSet();

    abstract public Long getGroupId();

    public void save(long articleId, File file) {
        StringBuilder sentenceStr = new StringBuilder();
        try (
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), getCharSet());
                BufferedReader is = new BufferedReader(isr);
        ) {
            String line;
            while (null!=(line = is.readLine())){
                if(!getPattern().matcher(line).find()){
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
