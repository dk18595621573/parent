package com.cloud.webmvc.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 读取项目相关配置
 *
 * @author author
 */
@Data
@ConfigurationProperties(prefix = "cloud.system")
public class SystemProperties {
    /**
     * 项目名称
     */
    private String name;

    /**
     * 版本
     */
    private String version;

    /**
     * swagger文档开关
     */
    private boolean swaggerEnabled;

    /**
     * 本项目域名
     */
    private String domain;

    /** 订阅地址(快递100) */
    private String callbackUrl;

    /**
     * 移动端域名
     */
    private String mobileDomain;

    /**
     * token配置
     */
    private TokenProperties token = new TokenProperties();

    /**
     * 权限相关配置
     */
    private SecurityProperties security = new SecurityProperties();

    /**
     * xss配置
     */
    private XssProperties xss = new XssProperties();

    /**
     * 获取地址开关
     */
    private boolean addressEnabled;

    /**
     * 验证码类型
     */
    private String captchaType;

}
