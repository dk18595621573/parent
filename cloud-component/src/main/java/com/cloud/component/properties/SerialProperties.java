package com.cloud.component.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 手机序列号查询--3202DATA配置
 */
@Data
@ConfigurationProperties(prefix = SerialProperties.SERIAL_PREFIX)
public class SerialProperties {
    public static final String SERIAL_PREFIX = "cloud.serial";
    /**
     *  请求路径
     */
    private String url;

    /**
     *  密钥
     */
    private String key;
}
