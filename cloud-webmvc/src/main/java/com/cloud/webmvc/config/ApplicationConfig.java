package com.cloud.webmvc.config;

import com.cloud.common.config.SystemConfig;
import com.cloud.framework.redis.RedisCache;
import com.cloud.webmvc.security.service.TokenStrategy;
import com.cloud.webmvc.security.service.strategy.RedisTokenStrategy;
import com.cloud.webmvc.security.service.strategy.SimpleTokenStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.TimeZone;

/**
 * 程序注解配置
 *
 * @author author
 */
@Configuration
public class ApplicationConfig {

    /**
     * 系统配置.
     */
    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    private RedisCache redisCache;

    /**
     * 时区配置
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());
    }


    @Bean
    public TokenStrategy tokenStrategy() {
        if (systemConfig.getToken().isCached()) {
            return new RedisTokenStrategy(redisCache, systemConfig.getToken());
        }
        return new SimpleTokenStrategy(systemConfig.getToken());
    }

    /**
     * 强散列哈希加密实现
     */
    @Bean
    @ConditionalOnMissingBean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
