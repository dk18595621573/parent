package com.cloud.component.config;

import com.cloud.component.huasheng.HSClient;
import com.cloud.component.huasheng.util.HSUtil;
import com.cloud.component.properties.HSProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 华盛配置
 *
 */
@Configuration
@EnableConfigurationProperties(value = HSProperties.class)
public class HSConfig {

    @Bean
    public HSClient hsClient(HSProperties hsProperties) {
        return new HSClient(hsProperties);
    }

    @Bean
    public HSUtil hsUtil(HSProperties hsProperties) {
        return new HSUtil(hsProperties);
    }
}
