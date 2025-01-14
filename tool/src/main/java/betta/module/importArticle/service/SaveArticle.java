package betta.module.importArticle.service;

import betta.domain.AjaxResult;
import betta.domain.EngArticle;
import betta.module.importArticle.service.sentence.RaymondSaveSentence;
import betta.module.importArticle.service.sentence.SaveSentence;
import betta.service.RestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class SaveArticle implements ApplicationContextAware {

    @Autowired
    private RestService restService;

    private ApplicationContext context;

    @Autowired
    private SaveMp3 saveMp3;

    public void save(Map<String, List<File>> map, String title) {
        SaveSentence saveSentence = context.getBean(title, SaveSentence.class);
        if (saveSentence == null) {
            log.error("片名参数不合法：{}", title);
            throw new RuntimeException("片名参数不合法：" + title);
        }
        log.info("文件信息：{}", map);
        int count = 0;
        List<String> fileNames = new ArrayList<>(map.keySet().size());
        for (Map.Entry<String, List<File>> entry : map.entrySet()) {
            String fileName = entry.getKey();
            List<File> files = entry.getValue();

            //保存文章信息
            EngArticle article = new EngArticle();
            article.setGroupId(saveSentence.getGroupId());
            article.setTitle(fileName);
            AjaxResult ajaxResult = restService.post("/eng/article", article);
            Object object = ajaxResult.get(AjaxResult.DATA_TAG);
            if (object != null) {
                LinkedHashMap data = (LinkedHashMap) object;
                log.info("文章保存成功：｛｝" + data);
                Integer articleId = (Integer) data.get("id");

                for (File file : files) {
                    //保存句子信息
                    if (file.getName().endsWith(".lrc")) {
                        saveSentence.save(articleId, file);
                    } else if (file.getName().endsWith(".mp3")) {
                        //保存mp3信息
                        saveMp3.save(articleId, file);
                    }
                }
                count++;
                fileNames.add(fileName);
            }
        }
        log.info("操作完成，共添加{}篇文章,名称为 {}", count, StringUtils.joinWith(",", fileNames));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
