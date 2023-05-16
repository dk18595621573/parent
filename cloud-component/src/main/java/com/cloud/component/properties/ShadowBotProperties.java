package com.cloud.component.properties;

import com.cloud.common.constant.Constants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 影刀RPA 配置 属性.
 *
 * @author Luo
 * @date 2023-3-17 13:14:20
 */
@Data
@ConfigurationProperties(prefix = ShadowBotProperties.PREFIX)
public class ShadowBotProperties {

    public static final String PREFIX = Constants.CONFIG_PREFIX + "shadow";

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
