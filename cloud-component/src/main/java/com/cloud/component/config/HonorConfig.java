package com.cloud.component.config;

import com.cloud.component.honor.HonorClient;
import com.cloud.component.properties.HonorProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 荣耀配置.
 *
 * @author Luo
 * @date 2023-3-17 13:18:04
 */
@Configuration
@ConditionalOnProperty(prefix = HonorProperties.HONOR_PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties(value = HonorProperties.class)
public class HonorConfig {

    /**
     * 荣耀客户端.
     *
     * @param honorProperties 荣耀配置
     * @return 结果
     */
    @Bean
    public HonorClient honorClient(HonorProperties honorProperties) {
        return new HonorClient(honorProperties);
    }

}
