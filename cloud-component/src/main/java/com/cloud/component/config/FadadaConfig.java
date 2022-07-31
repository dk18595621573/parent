package com.cloud.component.config;

import com.cloud.component.fadada.FadadaClient;
import com.cloud.component.properties.FadadaProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 法大大相关配置.
 *
 * @author mft
 */
@Configuration
@EnableConfigurationProperties(value = FadadaProperties.class)
public class FadadaConfig {

    @Bean
    @ConditionalOnProperty(prefix = FadadaProperties.FADADA_PREFIX, name = "enabled", havingValue = "true")
    public FadadaClient fadadaClient(FadadaProperties fadadaProperties) {
        return new FadadaClient(fadadaProperties);
    }
}
