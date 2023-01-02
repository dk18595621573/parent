package com.cloud.component.properties;

import com.cloud.common.constant.Constants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *  华盛配置属性
 */
@Data
@ConfigurationProperties(prefix = HSProperties.HS_PREFIX)
public class HSProperties {

    public static final String HS_PREFIX = Constants.CONFIG_PREFIX + "hs";

    /**
     * 华盛服务器路径
     */
    public String url;

    /**
     * 密钥
     */
    private String appkey;

    /**
     * 加密
     */
    private String secret;

    /**
     * 接口版本
     */
    private String version;
}
