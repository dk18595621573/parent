package com.cloud.core;

import com.cloud.core.config.RedisConfig;
import com.cloud.core.config.ThreadPoolConfig;
import com.cloud.core.log.LogService;
import com.cloud.core.log.SimpleLogServiceImpl;
import com.cloud.core.manager.ShutdownManager;
import com.cloud.core.utils.SpringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * 自动注册框架.
 *
 * @author zenghao
 * @date 2022/7/3
 */
@Configuration
// 表示通过aop框架暴露该代理对象,AopContext能够访问
@EnableAspectJAutoProxy(exposeProxy = true)
@Import({RedisConfig.class, ThreadPoolConfig.class, SpringUtils.class})
public class CoreAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public LogService logService() {
        return new SimpleLogServiceImpl();
    }

    @Bean
    public ShutdownManager shutdownManager() {
        return new ShutdownManager();
    }
}
