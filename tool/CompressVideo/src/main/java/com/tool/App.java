package com.tool;

import com.tool.util.CompressThread;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 压缩视频工具
 * 使用方法： 1. 修改path为要压缩的视频文件位置
 * 2. 压缩完后存放在原目录的after文件夹下
 */
public class App {

    public static AtomicInteger second = new AtomicInteger(0);

    private static final List<String> PATHS = List.of("C:\\Users\\Administrator\\Videos\\新建文件夹");
    //转换后存放位置
    //String targetPath = path+ "\\after\\";
    public static final List<String> TARGET_PATHS = List.of("C:\\Users\\Administrator\\Videos\\新建文件夹1");

    //存放处理失败的文件的全路径文件名
    public static final List<String> FAIL_LIST = new ArrayList<>();

    //存放成功的文件的全路径文件名
    public static final List<String> SUCCESS_LIST = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        long time = System.currentTimeMillis();
        FAIL_LIST.clear();
        
        // 优化线程池配置：根据CPU核心数动态设置
        int corePoolSize = Math.max(4, Runtime.getRuntime().availableProcessors());
        int maximumPoolSize = corePoolSize * 2;

        for (int i = 0; i < PATHS.size(); i++) {
            String path = PATHS.get(i);
            String targetPath = TARGET_PATHS.get(i);
            System.out.println("path:"+path+"\ntargetPath:"+targetPath);

            ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 60, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(500), Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.CallerRunsPolicy());
            // 压缩前文件路径
            File dir = new File(path);
            // 在开始压缩前创建全局tmp文件夹
            createGlobalTmpFolder(targetPath);

            File[] files = dir.listFiles();
            if (files.length > 0) {
                for (File source : files) {
                    compress(source, pool,targetPath,path);
                }
            }
            pool.shutdown(); // 停止接受新任务
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS); // 等待所有任务完成

            // 所有压缩完成后删除tmp文件夹
            deleteGlobalTmpFolder(targetPath);
        }
        System.out.println("转换完成, 共耗时：" + (double) (System.currentTimeMillis() - time) / 1000/60.00 + "分钟");
        System.out.println("共转换：" + second.intValue() / 60 + "分钟的视频");
        //打印转换失败的文件名
        System.out.println("-----------转换失败的文件"+FAIL_LIST.size()+"个--------");

        for (String fail : FAIL_LIST) {
            System.out.println(fail);
        }
        System.out.println("-------------------------------------------------------");
        //打印转换成功的文件名
        System.out.println("-----------转换成功的文件"+SUCCESS_LIST.size()+"个--------");
        for (String success : SUCCESS_LIST) {
            System.out.println(success);
        }


    }

    private static void compress(File source, ThreadPoolExecutor pool,String targetPath,String path) {
        if (source.isDirectory()) {
            File[] files = source.listFiles();
            if (files != null) {
                for (File file : files) {
                    compress(file, pool, targetPath, path);
                }
            }
        } else if (source.getName().matches("^[\\w\\W]+\\.(?i)(mp4|avi|mov|wmv|rmvb|rm|flv|mkv|mpeg|mpg|m4v|3gp|3g2|m2ts|mts|m2v|m2t|m2a|m2p|m2ts|m2p|m2v|m2p|m2t|m2a|m2p|m2v|m2p|m2t|m2a|m2p|m2v|m2p|m2t|m2a|m2p|m2v|m2p|m2t|m2a|m2p|m2v|m2p|m2t|m2a|m2p|m2v|m2p|m2t|m2a|m2p|m2v|m2p|m2t|m2a|m2p|m2v|m2p|m2t|m2a|m2p|m2v|m2p|m2t|m2a|m2p|m2v|m2p|m2t)$")) {
            String target = targetPath + source.getParent().substring(path.length());
            // 检查目标文件是否已存在，避免重复压缩
            File targetFile = new File(target + "\\" + source.getName());
            if (targetFile.exists()) {
                System.out.println("文件已存在，跳过: " + source.getName());
                return;
            }
            pool.execute(new CompressThread(source, target,getTmpPath(targetPath)));
        }
    }

    public static String getTmpPath(String targetPath) {
        return targetPath + "\\tmp\\";
    }

    /**
     * 创建全局tmp文件夹
     */
    private static void createGlobalTmpFolder(String targetPath) {
        File tmpDir = new File(getTmpPath(targetPath));
        if (!tmpDir.exists()) {
            if (tmpDir.mkdirs()) {
                System.out.println("创建临时文件夹: " + tmpDir.getAbsolutePath());
            } else {
                System.out.println("创建临时文件夹失败: " + tmpDir.getAbsolutePath());
            }
        } else {
            System.out.println("临时文件夹已存在: " + tmpDir.getAbsolutePath());
        }
    }

    /**
     * 递归删除全局tmp文件夹及其内容
     */
    private static void deleteGlobalTmpFolder(String targetPath) {
        File tmpDir = new File(getTmpPath(targetPath));
        if (tmpDir.exists() && tmpDir.isDirectory()) {
            if (deleteDirectory(tmpDir)) {
                System.out.println("删除临时文件夹成功: " + tmpDir.getAbsolutePath());
            } else {
                System.out.println("删除临时文件夹失败: " + tmpDir.getAbsolutePath());
            }
        }
    }

    /**
     * 递归删除目录及其所有内容
     */
    private static boolean deleteDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    if (!file.delete()) {
                        System.out.println("删除文件失败: " + file.getAbsolutePath());
                        return false;
                    }
                }
            }
        }
        return directory.delete();
    }


}
