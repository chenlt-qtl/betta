package betta.module.importArticle.service;

import betta.domain.AjaxResult;
import betta.service.RestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.LinkedHashMap;

@Component
@Slf4j
public class SaveMp3 {

    @Autowired
    private RestService restService;
    public void save(int articleId,File file){
        AjaxResult result = restService.uploadFile("/common/upload/article", file);
        if(result.isSuccess()){
            String url = (String) result.get("url");
            //取出文章数据
            AjaxResult articleResult = restService.get("/eng/article/"+articleId,null);
            LinkedHashMap article = (LinkedHashMap) articleResult.get(AjaxResult.DATA_TAG);

            //更新mp3字段后保存
            article.put("mp3",url);
            restService.put("/eng/article", article);
        }else {
            log.error("文件上传失败");
        }
    }
}
