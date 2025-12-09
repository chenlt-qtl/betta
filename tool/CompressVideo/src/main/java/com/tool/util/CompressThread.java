package com.tool.util;

import com.tool.App;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;


public class CompressThread implements Runnable {

    private File source;
    private String targetPath;
    private String tmpPath;

    public CompressThread(File source, String targetPath,String tmpPath) {
        this.source = source;
        this.targetPath = targetPath;
        this.tmpPath = tmpPath;

    }

    @Override
    public void run() {
        String prefix = Thread.currentThread().getName() + "---" + this.source.getName() + "---";

        // 压缩后的文件临时路径
        File tempTarget = new File(tmpPath + "\\" + source.getName());
        
        // 最终目标路径
        File finalTarget = new File(targetPath + "\\" + source.getName());
        
        // 压缩到临时文件
        File result = CompressionVideoUtil.compressionVideo(source, tempTarget, prefix);
        
        if (result != null && result.exists() && result.equals(tempTarget)) {
            // 压缩成功，移动文件到最终目标位置
            if (!finalTarget.getParentFile().exists()) {
                finalTarget.getParentFile().mkdirs();
            }
            
            // 尝试移动文件，如果失败则尝试复制后删除
            boolean moveSuccess = false;
            try {
                moveSuccess = tempTarget.renameTo(finalTarget);
                
                if (!moveSuccess) {
                    System.out.println(prefix + "renameTo失败，尝试复制后删除...");
                    // 备选方案：复制文件后删除原文件
                    moveSuccess = copyFile(tempTarget, finalTarget);
                    if (moveSuccess) {
                        tempTarget.delete();
                    }
                }
                
                if (moveSuccess) {
                    System.out.println(prefix + "文件移动成功: " + finalTarget.getAbsolutePath());
                } else {
                    System.out.println(prefix + "文件移动失败，保留在临时位置: " + tempTarget.getAbsolutePath());
                    System.out.println(prefix + "失败原因 - 目标存在:" + finalTarget.exists() + 
                                     ", 目标目录可写:" + finalTarget.getParentFile().canWrite() +
                                     ", 源文件可读:" + tempTarget.canRead());
                    App.FAIL_LIST.add(source.getAbsolutePath() + " (移动失败)");
                }
            } catch (Exception e) {
                System.err.println(prefix + "移动文件时发生异常: " + e.getMessage());
                App.FAIL_LIST.add(source.getAbsolutePath() + " (移动异常: " + e.getMessage() + ")");
            }
        } else {
            System.out.println(prefix + "压缩失败");
        }
    }

    /**
     * 复制文件的备选方案
     */
    private boolean copyFile(File source, File target) {
        FileChannel sourceChannel = null;
        FileChannel targetChannel = null;
        
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            targetChannel = new FileOutputStream(target).getChannel();
            targetChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            return true;
        } catch (IOException e) {
            System.err.println("复制文件失败: " + e.getMessage());
            return false;
        } finally {
            try {
                if (sourceChannel != null) sourceChannel.close();
                if (targetChannel != null) targetChannel.close();
            } catch (IOException e) {
                System.err.println("关闭文件通道失败: " + e.getMessage());
            }
        }
    }

}
