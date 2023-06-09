package com.cloud.component.properties;

import com.cloud.common.constant.Constants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
import java.util.Map;

/**
 * 企业机器人配置.
 *
 * @author zenghao
 * @date 2022/8/29
 */
@Data
@ConfigurationProperties(prefix = WxworkBotProperties.BOT_PREFIX)
public class WxworkBotProperties {
    public static final String BOT_PREFIX = Constants.CONFIG_PREFIX + "bot";

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
     * 机器人配置
     */
    private Map<String, BotConfig> configs;

    /**
     * 发送消息参数
     */
    private OutMessage message = new OutMessage();

    /**
     * 小程序消息配置
     */
    @Data
    public static class OutMessage implements Serializable {

        /**
         * 小程序appid
         */
        private String appid;

        /**
         * 标题
         */
        private String title;

        /**
         * 小程序原始id
         */
        private String ghId;
    }

    /**
     * 机器人配置
     */
    @Data
    public static class BotConfig implements Serializable {

        /**
         * 机器人账号id
         */
        private String userId;

        /**
         * 机器人配置token
         */
        private String token;
    }


}
