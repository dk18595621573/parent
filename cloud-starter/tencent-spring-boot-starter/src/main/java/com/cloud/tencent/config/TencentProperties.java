package com.cloud.tencent.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TODO auto.
 *
 * @author zenghao
 * @date 2022/7/17
 */
@Data
@ConfigurationProperties(prefix = TencentProperties.TENCENT_PREFIX)
public class TencentProperties {

    public static final String TENCENT_PREFIX = "cloud.tencent";

    /**
     * 密钥id.
     */
    private String secretId;

    /**
     * 密钥key.
     */
    private String secretKey;

    /**
     * 设置 bucket 的区域.
     * COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
     */
    private String region;
}
