package com.cloud.component.config;

import com.cloud.component.fadada.FadadaClient;
import com.cloud.component.properties.FadadaProperties;
import com.fadada.sdk.base.client.FddBaseClient;
import com.fadada.sdk.verify.client.FddVerifyClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
    private static final String V = "2.0";
    /**
     * 法大大请求参数
     */
    @Bean
    @ConditionalOnProperty(prefix = FadadaProperties.FADADA_PREFIX, name = "enabled", havingValue = "true")
    public FddVerifyClient fddVerifyClient(FadadaProperties fadadaProperties) {
        return new FddVerifyClient(fadadaProperties.getAddId(), fadadaProperties.getAppKey(), V, fadadaProperties.getHost());
    }
    @Bean
    @ConditionalOnProperty(prefix = FadadaProperties.FADADA_PREFIX, name = "enabled", havingValue = "true")
    public FddBaseClient fddBaseClient(FadadaProperties fadadaProperties) {
        return new FddBaseClient(fadadaProperties.getAddId(), fadadaProperties.getAppKey(), V, fadadaProperties.getHost());
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = FadadaProperties.FADADA_PREFIX, name = "enabled", havingValue = "true")
    public FadadaClient fadadaClient(FddVerifyClient fddVerifyClient, FddBaseClient fddBaseClient, FadadaProperties fadadaProperties) {
        return new FadadaClient(fddVerifyClient, fddBaseClient, fadadaProperties);
    }

}
