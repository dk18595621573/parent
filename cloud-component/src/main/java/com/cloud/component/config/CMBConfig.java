package com.cloud.component.config;

import com.cloud.component.cmb.CMBClient;
import com.cloud.component.properties.CMBProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 招商银行配置
 *
 */
@Configuration
@ConditionalOnProperty(prefix = CMBProperties.CMB_PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties(value = CMBProperties.class)
public class CMBConfig {

    @Bean
    public CMBClient cmbClient(CMBProperties cmbProperties) {
        return new CMBClient(cmbProperties);
    }
}
