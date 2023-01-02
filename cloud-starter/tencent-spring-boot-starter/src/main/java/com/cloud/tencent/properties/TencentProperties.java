package com.cloud.tencent.properties;

import com.cloud.common.constant.Constants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 腾讯云基本配置.
 *
 * @author zenghao
 * @date 2022/7/17
 */
@Data
@ConfigurationProperties(prefix = TencentProperties.TENCENT_PREFIX)
public class TencentProperties {

    public static final String TENCENT_PREFIX = Constants.CONFIG_PREFIX + "tencent";

    /**
     * 密钥id.
     */
    private String secretId;

    /**
     * 密钥key.
     */
    private String secretKey;

}
