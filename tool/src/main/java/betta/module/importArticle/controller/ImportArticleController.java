package betta.module.importArticle.controller;


import betta.domain.AjaxResult;
import betta.module.importArticle.service.SaveArticle;
import betta.module.importArticle.service.ReadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;
import java.util.Map;


/**
 * 保存英语文章
 */
@RestController
public class ImportArticleController {

    @Autowired
    private SaveArticle saveArticle;

    @PostMapping("/imposrtArticle/{title}")
    //title raymond 或者 friends
    public AjaxResult importArticle(@PathVariable String title) {
        //从文件夹中读取文本文件 mp3文件
        Map<String, List<File>> map = ReadFileService.readFields("E:\\备份\\article");
        //保存文章信息 标题同文件名
        saveArticle.save(map, title);
        return AjaxResult.success();
    }
}
