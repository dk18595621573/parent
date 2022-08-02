package com.cloud.component.config;

import com.cloud.component.express.ExpressClient;
import com.cloud.component.properties.ExpressProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 快递查询自动装载.
 *
 * @author zenghao
 * @date 2022/7/19
 */
@Configuration
@EnableConfigurationProperties(value = ExpressProperties.class)
@ConditionalOnProperty(prefix = ExpressProperties.EXPRESS_PREFIX, name = "enabled", havingValue = "true")
public class ExpressConfig {

    @Bean
    public ExpressClient expressClient(ExpressProperties properties) {
        return new ExpressClient(properties);
    }
}
