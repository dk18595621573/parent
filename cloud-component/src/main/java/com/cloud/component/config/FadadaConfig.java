package com.cloud.component.config;

import com.cloud.component.fadada.FadadaClient;
import com.cloud.component.properties.FadadaProperties;
import com.fadada.sdk.base.client.FddBaseClient;
import com.fadada.sdk.verify.client.FddVerifyClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
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
@ConditionalOnProperty(prefix = FadadaProperties.FADADA_PREFIX, name = "enabled", havingValue = "true")
public class FadadaConfig {
    private static final String VERSION = "2.0";
    /**
     * 法大大请求参数
     */
    @Bean
    @ConditionalOnClass(name = "com.fadada.sdk.client.FddClient")
    public FddVerifyClient fddVerifyClient(FadadaProperties fadadaProperties) {
        return new FddVerifyClient(fadadaProperties.getAddId(), fadadaProperties.getAppKey(), VERSION, fadadaProperties.getHost());
    }

    @Bean
    @ConditionalOnClass(name = "com.fadada.sdk.client.FddClient")
    public FddBaseClient fddBaseClient(FadadaProperties fadadaProperties) {
        return new FddBaseClient(fadadaProperties.getAddId(), fadadaProperties.getAppKey(), VERSION, fadadaProperties.getHost());
    }

    @Bean
    @ConditionalOnMissingBean
    public FadadaClient fadadaClient(FddVerifyClient fddVerifyClient, FddBaseClient fddBaseClient, FadadaProperties fadadaProperties) {
        return new FadadaClient(fddVerifyClient, fddBaseClient, fadadaProperties);
    }

}
