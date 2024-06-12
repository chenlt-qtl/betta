package com.betta.common.utils.file;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.betta.common.config.BettaConfig;
import com.betta.common.constant.Constants;
import com.betta.common.utils.StringUtils;

/**
 * 图片处理工具类
 *
 * @author ruoyi
 */
public class ImageUtils
{
    private static final Logger log = LoggerFactory.getLogger(ImageUtils.class);

    public static final String DB_PATH_PRE = "/baseUrl";
    public static final Pattern BASE64_PATTERN = Pattern.compile("data\\:image/(jpeg|png|gif|jpg|bmp);base64\\,(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?");

    private static String htmlAppBaseApi = "[\\\\/]?" + BettaConfig.getVueAppBaseApi().replaceAll("^[\\\\/]", "");


    public static byte[] getImage(String imagePath)
    {
        InputStream is = getFile(imagePath);
        try
        {
            return IOUtils.toByteArray(is);
        }
        catch (Exception e)
        {
            log.error("图片加载异常 {}", e);
            return null;
        }
        finally
        {
            IOUtils.closeQuietly(is);
        }
    }

    public static InputStream getFile(String imagePath)
    {
        try
        {
            byte[] result = readFile(imagePath);
            result = Arrays.copyOf(result, result.length);
            return new ByteArrayInputStream(result);
        }
        catch (Exception e)
        {
            log.error("获取图片异常 {}", e);
        }
        return null;
    }

    /**
     * 读取文件为字节数据
     * 
     * @param url 地址
     * @return 字节数据
     */
    public static byte[] readFile(String url)
    {
        InputStream in = null;
        try
        {
            if (url.startsWith("http"))
            {
                // 网络地址
                URL urlObj = new URL(url);
                URLConnection urlConnection = urlObj.openConnection();
                urlConnection.setConnectTimeout(30 * 1000);
                urlConnection.setReadTimeout(60 * 1000);
                urlConnection.setDoInput(true);
                in = urlConnection.getInputStream();
            }
            else
            {
                // 本机地址
                String localPath = BettaConfig.getProfile();
                String downloadPath = localPath + StringUtils.substringAfter(url, Constants.RESOURCE_PREFIX);
                in = new FileInputStream(downloadPath);
            }
            return IOUtils.toByteArray(in);
        }
        catch (Exception e)
        {
            log.error("获取文件路径异常 {}", e);
            return null;
        }
        finally
        {
            IOUtils.closeQuietly(in);
        }
    }


    public static String webToDb(String text) {
        return webToDb(text, null);
    }

    /**
     * 前端地址转成数据库地址
     *
     * @param text
     * @param type
     * @return
     */
    public static String webToDb(String text, String type) {
        if (StringUtils.isBlank(text)) {
            return "";
        }

        log.info("=========≧◔◡◔≦=========text:", text);
        StringBuffer sbr = new StringBuffer();
        Pattern imgPattern;
        if ("html".equals(type)) {
            imgPattern = Pattern.compile("(?<=<img src=\")(" + htmlAppBaseApi + ")");
        } else if ("md".equals(type)) {
            imgPattern = Pattern.compile("(?<=][(])(" + BettaConfig.getVueAppBaseApi() + ")");
        } else {
            imgPattern = Pattern.compile("^(" + htmlAppBaseApi + ")");
        }
        Matcher matcher = imgPattern.matcher(text);
        while (matcher.find()) {
            log.info("============ContextPath:" + matcher.group(0));
            matcher.appendReplacement(sbr, DB_PATH_PRE);
        }

        matcher.appendTail(sbr);
        return sbr.toString();
    }

    /**
     * 将数据库里的图片等数据转成前端的地址
     *
     * @return
     */
    public static String dbToWeb(String text) {
        return dbToWeb(text, null);
    }

    /**
     * 将数据库里的图片等数据转成前端的地址
     *
     * @return
     */
    public static String dbToWeb(String text, String type) {
        if (StringUtils.isNotBlank(text)) {
            StringBuffer sbr = new StringBuffer();

            Pattern imgPattern;
            if ("html".equals(type)) {
                imgPattern = Pattern.compile("(?<=<img src=\")" + DB_PATH_PRE);
            } else if ("md".equals(type)) {
                imgPattern = Pattern.compile("(?<=][(])" + DB_PATH_PRE);
            } else {
                imgPattern = Pattern.compile(DB_PATH_PRE);
            }
            Matcher matcher = imgPattern.matcher(text);
            while (matcher.find()) {
                matcher.appendReplacement(sbr, BettaConfig.getVueAppBaseApi());
            }

            matcher.appendTail(sbr);
            return sbr.toString();
        } else {
            return "";
        }

    }

}
