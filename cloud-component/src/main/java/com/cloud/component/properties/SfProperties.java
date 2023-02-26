package com.cloud.component.properties;

import com.cloud.common.constant.Constants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 顺店通配置
 * @author nlsm
 */
@Data
@ConfigurationProperties(prefix = SfProperties.SF_PREFIX)
public class SfProperties {
    public static final String SF_PREFIX = Constants.CONFIG_PREFIX + "sf";

    private String url;

    private String appId;

    private String appSecret;

    private String senderOrgCode;

    private String appName;

}
