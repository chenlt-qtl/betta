package com.betta.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取项目相关配置
 *
 * @author ruoyi
 */
@Component
@ConfigurationProperties(prefix = "betta")
@Data
public class BettaConfig {
    /**
     * 项目名称
     */
    private String name;

    /**
     * 版本
     */
    private String version;

    /**
     * 版权年份
     */
    private String copyrightYear;

    /**
     * 上传路径
     */
    private static String profile;

    /**
     * 获取地址开关
     */
    private static boolean addressEnabled;

    /**
     * 验证码类型
     */
    private static String captchaType;

    private static String vueAppBaseApi;

    private String esHost;

    private int esPort;

    public static String getProfile() {
        return profile;
    }

    public static String getVueAppBaseApi() {
        return vueAppBaseApi;
    }

    public void setProfile(String profile) {
        BettaConfig.profile = profile;
    }

    public void setVueAppBaseApi(String vueAppBaseApi) {
        BettaConfig.vueAppBaseApi = vueAppBaseApi;
    }

    public static boolean isAddressEnabled() {
        return addressEnabled;
    }

    public static String getCaptchaType() {
        return captchaType;
    }


    /**
     * 获取导入上传路径
     */
    public static String getImportPath() {
        return getProfile() + "/import";
    }

    /**
     * 获取头像上传路径
     */
    public static String getAvatarPath() {
        return getProfile() + "/avatar";
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath() {
        return getProfile() + "/download/";
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath() {
        return getProfile() + "/upload";
    }

    /**
     * 单词MP3上传路径
     */
    public static String getWordPath() {
        return getProfile() + "/word";
    }

    /**
     * 文章相关上传路径
     */
    public static String getArticlePath() {
        return getProfile() + "/article";
    }

    /**
     * 笔记相关
     *
     * @return
     */
    public static String getNotePath() {
        return getProfile() + "/note";
    }

    /**
     * 公告相关上传路径
     */
    public static String getNoticePath() {
        return getProfile() + "/notice";
    }
}
