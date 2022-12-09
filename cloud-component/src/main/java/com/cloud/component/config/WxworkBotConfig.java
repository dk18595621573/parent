package com.cloud.component.config;

import com.cloud.component.bot.WxworkBotClient;
import com.cloud.component.properties.WxworkBotProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 企业微信机器人自动装配.
 *
 * @author zenghao
 * @date 2022/8/29
 */
@Configuration
@EnableConfigurationProperties(value = WxworkBotProperties.class)
@ConditionalOnProperty(prefix = WxworkBotProperties.BOT_PREFIX, name = "enabled", havingValue = "true")
public class WxworkBotConfig {

    @Bean
    @RefreshScope
    @ConditionalOnMissingBean
    public WxworkBotClient wxworkBotClient(WxworkBotProperties wxworkBotProperties) {
        return new WxworkBotClient(wxworkBotProperties);
    }
}
