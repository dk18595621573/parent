package com.cloud.tencent.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 腾讯云 cos相关配置.
 *
 * @author zenghao
 */
@Data
@ConfigurationProperties(prefix = CosProperties.COS_PREFIX)
public class CosProperties {

    public static final String COS_PREFIX = "cloud.tencent.cos";

    /**
     * 存储桶名称.
     */
    private String bucketName;

    /**
     * cos域名前缀.
     */
    private String cosDomain;
}
