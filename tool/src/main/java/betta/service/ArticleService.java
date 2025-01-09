package betta.service;


import betta.domain.AjaxResult;
import betta.utils.SaveUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ArticleService {


    @Autowired
    RestService restService;

    @Value("${corn.path.backup}")
    String backupPath;

    @Value("${corn.resourceUrl}")
    String resourceUrl;

    public void backupArticle() {
        //1. 从API读取数据
        int pageNo = 1;
        int pageSize = 20;
        int total = pageSize + 1;
        Map<String, Object> param = new HashMap();
        param.put("pageSize", pageSize);

        for (; total > (pageNo - 1) * pageSize; pageNo++) {
            param.put("pageNo", pageNo);
            AjaxResult result = restService.get("/word/article/list", param);
            Map page = (Map) result.get("data");
            List<Map> records = (List<Map>) page.get("records");
            total = (int) page.get("total");
            //2. 提取出mp3和图片

            for (Map article : records) {
                log.info("处理 article ：{}", article.get("title"));
                if(article.containsKey("mp3")) {
                    SaveUtil.saveFromUrl((String) article.get("mp3"),backupPath);
                }
                if(article.containsKey("picture")) {
                    SaveUtil.saveFromUrl((String) article.get("picture"),backupPath);
                }
            }
        }
        log.info("total : {}", total);

    }
}
