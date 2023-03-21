package com.cloud.component.properties;

import com.cloud.common.constant.Constants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 广移配置属性.
 *
 * @author Luo
 * @date 2023-3-17 13:14:20
 */
@Data
@ConfigurationProperties(prefix = ECSSProperties.ECSS_PREFIX)
public class ECSSProperties {

    public static final String ECSS_PREFIX = Constants.CONFIG_PREFIX + "ecss";

    /**
     * 接口请求地址.
     */
    private String url;

    /**
     * 分配给平台的 AppKey，由 ECSS 分配.
     */
    private String appKey;

    /**
     * 分配给平台的 app_secret(secret)，由 ECSS 分配.
     */
    private String appSecret;

    /**
     * 分配给用户的 SessionKey(或 Access Token）.
     */
    private String session;

    /**
     * Ecss分配，不用参加加密.
     */
    private String shopId;

}
