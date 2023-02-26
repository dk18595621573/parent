package com.cloud.component.config;

import com.cloud.component.express.SfClient;
import com.cloud.component.properties.SfProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 顺丰配置文件
 * @author nlsm
 */
@Configuration
@EnableConfigurationProperties(value = SfProperties.class)
@ConditionalOnProperty(prefix = SfProperties.SF_PREFIX, name = "enabled", havingValue = "true")
public class SfConfig {

    @Bean
    @RefreshScope
    public SfClient getSfClient(SfProperties sfProperties){
        return new SfClient(sfProperties);
    }
}
