package com.cloud.component.config;

import com.cloud.component.properties.SerialProperties;
import com.cloud.component.serial.SerialClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 序列化查询.
 *
 * @author zenghao
 * @date 2022/7/19
 */
@Configuration
@EnableConfigurationProperties(value = SerialProperties.class)
@ConditionalOnProperty(prefix = SerialProperties.SERIAL_PREFIX, name = "enabled", havingValue = "true")
public class SerialConfig {

    @Bean
    @RefreshScope
    public SerialClient serialClient(SerialProperties properties) {
        return new SerialClient(properties);
    }
}
