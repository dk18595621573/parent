package com.cloud.idempotent;

import com.cloud.core.redis.RedisCache;
import com.cloud.idempotent.aspect.IdempotentAspect;
import com.cloud.idempotent.service.IdempotentService;
import com.cloud.idempotent.service.IdempotentServiceImpl;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 业务幂等功能自动装配.
 *
 * @author zenghao
 * @date 2023/2/27
 */
@Configuration
public class IdempotentAutoConfiguration {


    /**
     * 幂等拦截器.
     *
     * @return 锁拦截器
     */
    @Bean
    public IdempotentAspect idempotentAspect(final IdempotentService idempotentService, final RedissonClient redissonClient) {
        return new IdempotentAspect(idempotentService, redissonClient);
    }

    /**
     * 幂等操作接口.
     *
     * @return 幂等操作接口
     */
    @Bean
    @ConditionalOnMissingBean
    public IdempotentService idempotentService(final RedisCache redisCache) {
        return new IdempotentServiceImpl(redisCache);
    }
}
