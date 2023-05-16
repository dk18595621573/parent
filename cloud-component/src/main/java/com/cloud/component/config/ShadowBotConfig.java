package com.cloud.component.config;

import com.cloud.component.properties.ShadowBotProperties;
import com.cloud.component.shadow.ShadowBotClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 影刀RPA配置.
 *
 * @author Luo
 * @date 2023-3-17 13:18:04
 */
@Configuration
@ConditionalOnProperty(prefix = ShadowBotProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties(value = ShadowBotProperties.class)
public class ShadowBotConfig {

    /**
     * 影刀RPA客户端.
     *
     * @param shadowBotProperties 影刀RPA配置
     * @return 结果
     */
    @Bean
    public ShadowBotClient shadowBotClient(ShadowBotProperties shadowBotProperties) {
        return new ShadowBotClient(shadowBotProperties);
    }

}
