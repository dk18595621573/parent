package com.cloud.component.config;

import com.cloud.component.ecss.ECSSClient;
import com.cloud.component.properties.ECSSProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 广移配置.
 *
 * @author Luo
 * @date 2023-3-17 13:18:04
 */
@Configuration
@ConditionalOnProperty(prefix = ECSSProperties.ECSS_PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties(value = ECSSProperties.class)
public class ECSSConfig {

    @Bean
    public ECSSClient ecssClient(ECSSProperties ecssProperties) {
        return new ECSSClient(ecssProperties);
    }

}
