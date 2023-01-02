package com.cloud.component.properties;

import com.cloud.common.constant.Constants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 法大大相关配置.
 *
 * @author mft
 */
@Data
@ConfigurationProperties(prefix = FadadaProperties.FADADA_PREFIX)
public class FadadaProperties {

    public static final String FADADA_PREFIX = Constants.CONFIG_PREFIX + "fadada";
    /**
     * 注册appId
     */
    private String addId;

    /**
     * 注册appKey
     */
    private String appKey;

    /**
     * 地址
     */
    private String host;

    /**
     * 认证类型
     */
    private String type;

    /**
     * 请求版本
     */
    private String version;

}
