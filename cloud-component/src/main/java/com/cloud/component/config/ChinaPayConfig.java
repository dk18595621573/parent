package com.cloud.component.config;

import com.cloud.component.chinapay.ChinaPayClient;
import com.cloud.component.properties.ChinaPayProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 银联相关配置.
 *
 * @author zenghao
 * @date 2022/6/20
 */
@Configuration
@ConditionalOnClass(name = "com.chinapay.secss.SecssUtil")
@ConditionalOnProperty(prefix = ChinaPayProperties.CHINAPAY_PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties(value = ChinaPayProperties.class)
public class ChinaPayConfig {

    @Bean
    @RefreshScope
    public ChinaPayClient chinaPayClient(ChinaPayProperties chinaPayProperties) {
        return new ChinaPayClient(chinaPayProperties);
    }
}
