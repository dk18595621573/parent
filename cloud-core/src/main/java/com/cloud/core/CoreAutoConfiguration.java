package com.cloud.core;

import com.cloud.core.log.LogService;
import com.cloud.core.log.SimpleLogServiceImpl;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 自动注册框架.
 *
 * @author zenghao
 * @date 2022/7/3
 */
@Configuration
// 表示通过aop框架暴露该代理对象,AopContext能够访问
@EnableAspectJAutoProxy(exposeProxy = true)
@AutoConfigurationPackage(basePackageClasses = CoreAutoConfiguration.class)
public class CoreAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public LogService logService() {
        return new SimpleLogServiceImpl();
    }
}
