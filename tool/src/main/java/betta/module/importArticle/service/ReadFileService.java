package betta.module.importArticle.service;


import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 保存英语文章
 * 1. 从一个文件夹中读取文本文件 mp3文件
 * 2. 标题同文件名  保存文章信息
 * 3. 去掉一些非法行
 * 4. 使用接口导入句子
 * 5. 保存生词
 */

@Slf4j
public class ReadFileService {

    /**
     * 从文件夹中读取文本和MP3
     * @param
     * @return
     */
    public static Map<String, List<File>> readFields(String url) {
        Map<String,List<File>> result = new HashMap<>();
        File root = new File(url);
        File[] files = root.listFiles();
        for (File file : files) {
            String name = file.getName();
            log.info("读取到文件:{}"+name);
            String key = name.substring(0,name.lastIndexOf("."));
            List<File> articleFile;
            if(result.containsKey(key)){
                articleFile = result.get(key);
            }else {
                articleFile = new ArrayList<>(2);
                result.put(key,articleFile);
            }
            articleFile.add(file);
        }

        return result;
    }
}
