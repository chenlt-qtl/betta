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

        // 创建临时文件夹
        File tmpDir = new File(targetPath + "\\tmp");
        if (!tmpDir.exists()) {
            tmpDir.mkdirs();
        }
        
        // 压缩后的文件临时路径
        File tempTarget = new File(targetPath + "\\tmp\\" + source.getName());
        
        // 最终目标路径
        File finalTarget = new File(targetPath + "\\" + source.getName());
        
        // 检查最终目标文件是否已存在
        if (finalTarget.exists()) {
            System.out.println(prefix + "文件已存在，跳过");
            return;
        }
        
        // 压缩到临时文件
        File result = CompressionVideoUtil.compressionVideo(source, tempTarget, prefix);
        
        if (result != null && result.exists() && result.equals(tempTarget)) {
            // 压缩成功，移动文件到最终目标位置
            if (!finalTarget.getParentFile().exists()) {
                finalTarget.getParentFile().mkdirs();
            }
            
            if (tempTarget.renameTo(finalTarget)) {
                System.out.println(prefix + "文件移动成功: " + finalTarget.getAbsolutePath());
                // 删除临时文件夹（如果为空）
                deleteEmptyDirectory(tmpDir);
            } else {
                System.out.println(prefix + "文件移动失败，保留在临时位置: " + tempTarget.getAbsolutePath());
                App.FAIL_LIST.add(source.getAbsolutePath() + " (移动失败)");
            }
        } else {
            System.out.println(prefix + "压缩失败");
        }
    }
    
    /**
     * 递归删除空目录
     */
    private void deleteEmptyDirectory(File directory) {
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files == null || files.length == 0) {
                if (directory.delete()) {
                    System.out.println("删除空目录: " + directory.getAbsolutePath());
                    // 递归删除父目录（如果也为空）
                    File parent = directory.getParentFile();
                    if (parent != null && parent.getName().equals("tmp")) {
                        deleteEmptyDirectory(parent);
                    }
                }
            }
        }
    }

}
