package com.cloud.tencent.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 腾讯云 im 相关配置.
 *
 * @author Luo
 * @date 2023-3-27 13:46:51
 */
@Data
@ConfigurationProperties(prefix = ImProperties.IM_PREFIX)
public class ImProperties {

    public static final String IM_PREFIX = TencentProperties.TENCENT_PREFIX + ".im";

    /**
     * 请求地址.
     */
    private String url;

    /**
     * 应用AppId.
     */
    private Integer sdkAppId;

    /**
     * 秘钥.
     */
    private String secretKey;

    /**
     * 管理员用户Id.
     */
    private String adminUserId;

}
