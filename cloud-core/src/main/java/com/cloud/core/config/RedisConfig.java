package com.cloud.core.config;

import com.cloud.core.redis.JsonRedisTemplate;
import com.cloud.core.redis.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.spring.starter.RedissonAutoConfigurationCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * redis配置
 *
 * @author author
 */
@Slf4j
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
    @Bean
    @ConditionalOnMissingBean
    public RedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {
        return new JsonRedisTemplate(connectionFactory);
    }

    @Bean
    public RedissonAutoConfigurationCustomizer jsonCustomizer() {
        return (c) -> c.setCodec(new JsonJacksonCodec());
    }

    @Bean
    public RedisCache redisCache(RedisTemplate redisTemplate) {
        return new RedisCache(redisTemplate);
    }

}
