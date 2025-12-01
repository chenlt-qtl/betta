package com.tool.util;

import com.tool.App;

import java.io.File;
import java.io.InputStream;
import java.util.regex.Pattern;

public class CompressThread implements Runnable {

    private File source;
    private String targetPath;

    public CompressThread(File source, String targetPath) {
        this.source = source;
        this.targetPath = targetPath;
    }

    @Override
    public void run() {
        String prefix = Thread.currentThread().getName() + "---" + this.source.getName() + "---";
        System.out.println(prefix + "开始压缩文件:" + source.getName() + "-----------------------------");

        // 压缩后的文件临时路径
        File tempTarget = new File(targetPath + "\\tmp\\" + source.getName());
        
        // 最终目标路径
        File finalTarget = new File(targetPath + "\\" + source.getName());
        
        // 压缩到临时文件
        File result = CompressionVideoUtil.compressionVideo(source, tempTarget, prefix);
        
        if (result != null && result.exists() && result.equals(tempTarget)) {
            // 压缩成功，移动文件到最终目标位置
            if (!finalTarget.getParentFile().exists()) {
                finalTarget.getParentFile().mkdirs();
            }
            
            if (tempTarget.renameTo(finalTarget)) {
                System.out.println(prefix + "文件移动成功: " + finalTarget.getAbsolutePath());
            } else {
                System.out.println(prefix + "文件移动失败，保留在临时位置: " + tempTarget.getAbsolutePath());
                App.FAIL_LIST.add(source.getAbsolutePath() + " (移动失败)");
            }
        } else {
            System.out.println(prefix + "压缩失败");
        }
    }

}
