package com.cloud.component.config;

import com.cloud.component.properties.YaBaoProperties;
import com.cloud.component.yabao.YaBaoSerialClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 鸭宝串码查询.
 *
 * @author zenghao
 * @date 2023/2/17
 */
@Configuration
@EnableConfigurationProperties(value = YaBaoProperties.class)
@ConditionalOnProperty(prefix = YaBaoProperties.YA_BAO_PREFIX, name = "enabled", havingValue = "true")
public class YaBaoConfig {

    @Bean
    @RefreshScope
    @ConditionalOnMissingBean
    public YaBaoSerialClient yaBaoSerialClient(YaBaoProperties yaBaoProperties) {
        return new YaBaoSerialClient(yaBaoProperties);
    }
}
