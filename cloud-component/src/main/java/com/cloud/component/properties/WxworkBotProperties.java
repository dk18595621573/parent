package com.cloud.component.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 企业机器人配置.
 *
 * @author zenghao
 * @date 2022/8/29
 */
@Data
@ConfigurationProperties(prefix = WxworkBotProperties.BOT_PREFIX)
public class WxworkBotProperties {
    public static final String BOT_PREFIX = "cloud.bot";

    /**
     * 企微接口相关域名
     */
    private String apiDomain;

    /**
     * 企微接口 token
     */
    private String apiToken;

    /**
     * 机器人接口相关域名
     */
    private String botDomain;

    /**
     * 机器人接口 token
     */
    private String botToken;


}
