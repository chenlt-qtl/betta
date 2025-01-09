package betta.module.importArticle.service;

import betta.domain.AjaxResult;
import betta.domain.EngArticle;
import betta.service.RestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class SaveArticle {

    @Autowired
    private RestService restService;

    @Autowired
    private SaveSentence saveSentence;

    @Autowired
    private SaveMp3 saveMp3;

    public void save(Map<String, List<File>> map, long groupId) {
        log.info("文件信息：{}", map);
        map.forEach((fileName, files) -> {
            //保存文章信息
            EngArticle article = new EngArticle();
            article.setGroupId(groupId);
            article.setTitle(fileName);
            AjaxResult ajaxResult = restService.post("/eng/article", article);
            Object object = ajaxResult.get(AjaxResult.DATA_TAG);
            if (object != null) {
                LinkedHashMap data = (LinkedHashMap) object;
                log.info("文章保存成功：｛｝" + data);
                Integer articleId = (Integer) data.get("id");

                for (File file : files) {
                    //保存句子信息
                    if(file.getName().endsWith(".lrc")){
                        saveSentence.save(articleId,file);
                    }else if(file.getName().endsWith(".mp3")) {
                    //保存mp3信息
                        saveMp3.save(articleId,file);
                    }
                }
            }
        });

    }
}
