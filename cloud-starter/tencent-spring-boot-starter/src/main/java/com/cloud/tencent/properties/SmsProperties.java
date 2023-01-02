package com.cloud.tencent.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 腾讯云 短信相关配置.
 *
 * @author zenghao
 * @date 2022/7/17
 */
@Data
@ConfigurationProperties(prefix = SmsProperties.SMS_PREFIX)
public class SmsProperties {
    public static final String SMS_PREFIX = TencentProperties.TENCENT_PREFIX + ".sms";

    /**
     * 短信应用id
     */
    private String appid;

    /**
     * 设置区域.
     */
    private String region;

}
