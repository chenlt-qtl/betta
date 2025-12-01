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

    private static final String PATH = "I:\\【4.0】前端入门到精通";
    //转换后存放位置
    //String targetPath = path+ "\\after\\";
    private static final String TARGET_PATH = "I:\\【4.0】前端入门到精通1";

    //存放处理失败的文件的全路径文件名
    public static final List<String> FAIL_LIST = new ArrayList<>();

    //存放成功的文件的全路径文件名
    public static final List<String> SUCCESS_LIST = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        long time = System.currentTimeMillis();
        FAIL_LIST.clear();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 16, 120, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        // 压缩前文件路径
        File dir = new File(PATH);
        File[] files = dir.listFiles();
        if (files.length > 0) {
            for (File source : files) {
                compress(source, pool);
            }
        }
        pool.shutdown(); // 停止接受新任务
        pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS); // 等待所有任务完成
        System.out.println("转换完成, 共耗时：" + (double) (System.currentTimeMillis() - time) / 1000 + "秒");
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

    private static void compress(File source, ThreadPoolExecutor pool) {
        if (source.isDirectory()) {
            File[] files = source.listFiles();
            for (File file : files) {
                compress(file, pool);
            }
        } else if (source.getName().matches("^[\\w\\W]+\\.(?i)(mp4|avi)$")) {
            String target = TARGET_PATH + source.getParent().substring(PATH.length());
            pool.execute(new CompressThread(source, target));
        }
    }


}
