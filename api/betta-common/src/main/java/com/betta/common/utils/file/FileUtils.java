package com.betta.common.utils.file;

import com.betta.common.config.BettaConfig;
import com.betta.common.constant.Constants;
import com.betta.common.utils.DateUtils;
import com.betta.common.utils.SecurityUtils;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.net.URL;
import java.rmi.ServerException;
import java.util.Random;

/**
 * 文件处理工具类
 *
 * @author ruoyi
 */
public class FileUtils {

    private static Random RANDOM = new Random();

    /**
     * 写数据到文件中
     *
     * @param data 数据
     * @return 目标文件
     * @throws IOException IO异常
     */
    public static String writeImportBytes(byte[] data) throws IOException {
        String extension = getFileExtendName(data);
        String fileUri = Constants.IMPORT_PATH + "/" + genDateFileName(extension);

        File file = getFile(fileUri);

        try (
                BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(file));
        ) {
            fos.write(data);
        } catch (Exception e) {
            throw new ServerException("数据写入失败");
        }
        return fileUri;
    }


    /**
     * 生成文件路径和名字
     *
     * @param extension
     * @return
     */
    public static String genDateFileName(String extension) {
        String username = SecurityUtils.getUsername();

        return DateUtils.datePath() + "/" + username + "_" + (System.currentTimeMillis()-1729155670000L) +"_"+RANDOM.nextInt(100)+ "." + extension;
    }

    /**
     * 写数据到文件中
     *
     * @param url       文件URL地址
     * @return 目标文件
     * @throws IOException IO异常
     */
    public static void writeBytes(String url, String relativePath) throws IOException {

        String absolutePath = FileUtils.getAbsoluteProfilePath(relativePath);
        try (
                InputStream in = new URL(url).openConnection().getInputStream();//创建连接、输入流
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(absolutePath));
        ) {
            FileCopyUtils.copy(in, bos);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    public static File getFile(String fileUri) {
        File file = new File(fileUri);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return file;
    }

    /**
     * 獲取資源絕對路徑，如果目錄不存在則創建
     * @param relativePath
     * @return
     */
    public static String getAbsoluteProfilePath(String relativePath) {
        String absolutePath = BettaConfig.getProfile() + relativePath;
        File file = new File(absolutePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return absolutePath;
    }

    /**
     * 删除文件
     *
     * @param filePath 文件
     * @return
     */
    public static boolean deleteFile(String filePath) {
        boolean flag = false;
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            flag = file.delete();
        }
        return flag;
    }


    /**
     * 获取图像后缀
     *
     * @param photoByte 图像数据
     * @return 后缀名
     */
    public static String getFileExtendName(byte[] photoByte) {
        String strFileExtendName = "jpg";
        if ((photoByte[0] == 71) && (photoByte[1] == 73) && (photoByte[2] == 70) && (photoByte[3] == 56)
                && ((photoByte[4] == 55) || (photoByte[4] == 57)) && (photoByte[5] == 97)) {
            strFileExtendName = "gif";
        } else if ((photoByte[6] == 74) && (photoByte[7] == 70) && (photoByte[8] == 73) && (photoByte[9] == 70)) {
            strFileExtendName = "jpg";
        } else if ((photoByte[0] == 66) && (photoByte[1] == 77)) {
            strFileExtendName = "bmp";
        } else if ((photoByte[1] == 80) && (photoByte[2] == 78) && (photoByte[3] == 71)) {
            strFileExtendName = "png";
        }
        return strFileExtendName;
    }

    /**
     * 获取文件名称 /profile/upload/2022/04/16/ruoyi.png -- ruoyi.png
     *
     * @param fileName 路径名称
     * @return 没有文件路径的名称
     */
    public static String getFileName(String fileName) {
        if (fileName == null) {
            return null;
        }
        int lastUnixPos = fileName.lastIndexOf('/');
        int lastWindowsPos = fileName.lastIndexOf('\\');
        int index = Math.max(lastUnixPos, lastWindowsPos);
        return fileName.substring(index + 1);
    }

}
