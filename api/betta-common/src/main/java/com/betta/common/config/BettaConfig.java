package com.betta.common.config;

import com.betta.common.constant.Constants;
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

    public static String getVueResourcePath() {
        return vueAppBaseApi+ Constants.RESOURCE_PREFIX;
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



}
