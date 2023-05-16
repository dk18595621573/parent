package com.cloud.shadow.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 影刀RPA 配置 属性.
 *
 * @author Luo
 * @date 2023-5-16 17:15
 */
@Data
@ConfigurationProperties(prefix = ShadowProperties.PREFIX)
public class ShadowProperties {

    public static final String PREFIX = "shadow";

    /**
     * 接口请求地址.
     */
    private String url;

    /**
     * 密钥Key.
     */
    private String accessKeyId;

    /**
     * 密钥Secret.
     */
    private String accessKeySecret;

}
