package com.cloud.webmvc;

import com.cloud.core.redis.RedisCache;
import com.cloud.webmvc.properties.SystemProperties;
import com.cloud.webmvc.properties.TokenProperties;
import com.cloud.webmvc.service.strategy.RedisTokenStrategy;
import com.cloud.webmvc.service.strategy.SimpleTokenStrategy;
import com.cloud.webmvc.service.strategy.TokenStrategy;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * webmvc自动装配.
 *
 * @author zenghao
 * @date 2022/7/5
 */
@Configuration
@EnableConfigurationProperties(SystemProperties.class)
public class WebMvcAutoConfiguration {

    @Bean
    public TokenStrategy tokenStrategy(final SystemProperties systemProperties, final RedisCache redisCache) {
        final TokenProperties properties = systemProperties.getToken();
        if (properties.isCached()) {
            return new RedisTokenStrategy(redisCache, properties);
        }
        return new SimpleTokenStrategy(properties);
    }

}
