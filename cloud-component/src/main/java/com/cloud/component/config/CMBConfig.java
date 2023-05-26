package com.cloud.component.config;

import com.cloud.component.cmb.CMBClient;
import com.cloud.component.properties.CMBProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 招商银行配置.
 *
 * @author Luo
 * @date 2023-3-17 13:18:04
 */
@Configuration
@ConditionalOnProperty(prefix = CMBProperties.CMB_PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties(value = CMBProperties.class)
public class CMBConfig {

    /**
     * 招商银行客户端.
     *
     * @param cmbProperties 招商银行配置
     * @return 结果
     */
    @Bean
    @RefreshScope
    public CMBClient cmbClient(CMBProperties cmbProperties) {
        return new CMBClient(cmbProperties);
    }

}
