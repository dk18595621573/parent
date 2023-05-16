package com.cloud.shadow;

import com.cloud.shadow.client.ShadowBotClient;
import com.cloud.shadow.properties.ShadowProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 影刀自动装配.
 *
 * @author Luo
 * @date 2023-5-16 16:43
 */
@Configuration
@EnableConfigurationProperties(ShadowProperties.class)
@ConditionalOnProperty(prefix = ShadowProperties.PREFIX, name = "enabled", havingValue = "true")
public class ShadowAutoConfiguration {

    /**
     * 影刀RPA客户端.
     *
     * @param shadowProperties 影刀RPA配置
     * @return 结果
     */
    @Bean
    @ConditionalOnMissingBean
    public ShadowBotClient shadowBotClient(ShadowProperties shadowProperties) {
        return new ShadowBotClient(shadowProperties);
    }

}
