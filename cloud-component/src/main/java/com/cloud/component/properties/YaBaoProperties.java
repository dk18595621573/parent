package com.cloud.component.properties;

import com.cloud.common.constant.Constants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 手机序列号查询--鸭宝配置
 */
@Data
@ConfigurationProperties(prefix = YaBaoProperties.YA_BAO_PREFIX)
public class YaBaoProperties {
    public static final String YA_BAO_PREFIX = Constants.CONFIG_PREFIX + "yabao";
    /**
     *  请求路径
     */
    private String url;

    /**
     *  密钥
     */
    private String key;
}
