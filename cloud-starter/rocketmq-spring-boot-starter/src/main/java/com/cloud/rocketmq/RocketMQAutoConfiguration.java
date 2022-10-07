package com.cloud.rocketmq;

import com.cloud.core.redis.RedisCache;
import com.cloud.rocketmq.properties.RocketMQProperties;
import com.cloud.rocketmq.utils.RocketMQBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Supplier;

/**
 * cos服务自动配置.
 * @author zenghao
 */
@Configuration
@EnableConfigurationProperties(RocketMQProperties.class)
public class RocketMQAutoConfiguration {

    /**
     * 创建默认生产者，解决springfox【InMemorySwaggerResourcesProvider】兼容问题.
     *    Bean的名称和 配置文件中保持一致
     *
     * @return Credential
     */
    @Bean
    public Supplier<Message<String>> defaultSupplierProducer() {
        return () -> null;
    }

    @Bean
    public RocketMQBuilder rocketMQBuilder(RocketMQProperties properties, StreamBridge streamBridge, RedisCache redisCache) {
        return new RocketMQBuilder(properties, streamBridge, redisCache);
    }

}
