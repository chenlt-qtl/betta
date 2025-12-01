package com.tool.service;

import com.tool.util.PhotoUtils;

import java.io.File;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TidyFileService {

    /*根据文件名和大小整理文件，把文件名和大小一样的文件移动到新文件夹中*/
    public void tidyFileByName(File file, String targetPath) {
        Map<String, List<File>> map = new HashMap<>();
        fileToMap(file, map);
        for (Map.Entry<String, List<File>> entry : map.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.println(entry.getKey() + "共" + entry.getValue().size() + "个");
                //移动到targetPath
                File target = new File(targetPath);
                if (!target.exists()) {
                    target.mkdirs();
                }
                for (int i = 0; i < entry.getValue().size(); i++) {
                    File f = entry.getValue().get(i);
                    String name = f.getName();
                    int index = name.lastIndexOf(".");

                    String path = targetPath + File.separator + name.substring(0, index) + "-" + i +
                            name.substring(index);
                    File newFile = new File(path);
                    f.renameTo(newFile);
                }
            }
        }
    }

    private void fileToMap(File file, Map<String, List<File>> map) {
        if (file.isDirectory()) {
            //递归遍历文件夹
            for (File f : file.listFiles()) {
                fileToMap(f, map);
            }
        } else {
            if (file.getName().matches("^[\\w\\W]+\\.(?i)(jpg|png|mp4|avi|mov)$")) {
                List<File> list;
                if (map.containsKey(file.getName())) {
                    list = map.get(file.getName());
                } else {
                    list = new ArrayList<>();
                    map.put(file.getName(), list);
                }
                list.add(file);
            }
        }
    }

    /**
     * 根据日期整理文件到新文件夹中
     * 1. 根据拍摄日期，移动到对应日期的文件夹中
     */
    public void tidyFileByDate(File file, String targetPath) {
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                tidyFileByDate(f, targetPath);
            } else {
                //如果是图片或者视频，则移动到对应日期的文件夹中
                if (!f.getName().matches("^[\\w\\W]+\\.(?i)(jpg|png|mp4|avi)$")) {
                    continue;
                }
                String name = f.getName();
                Date date = PhotoUtils.getPhotoDate(f);
                if (date == null) {
                    continue;
                }

                //提取出date中的年份
                String year = date.toInstant().atZone(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern("yyyy"));
                //将date格式化为MMdd
                String dateStr = date.toInstant().atZone(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern("MMdd"));


                System.out.println("移动文件：" + f.getName() + "到" + targetPath + File.separator + year + File.separator + dateStr);
                //先新建年份的文件夹
                String path = targetPath + File.separator + year;
                File targetParent = new File(path);
                if (!targetParent.exists()) {
                    targetParent.mkdirs();
                }
                //再建日期文件夹
                path = path + File.separator + dateStr;
                File target = new File(path);
                if (!target.exists()) {
                    target.mkdirs();
                }
                //新文件
                File newFile = new File(path + File.separator + name);
                f.renameTo(newFile);
            }
        }
    }
}
