package com.cloud.framework.config;

import com.cloud.framework.log.LogService;
import com.cloud.framework.log.SimpleLogServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 程序注解配置
 *
 * @author author
 */
@Configuration
// 表示通过aop框架暴露该代理对象,AopContext能够访问
@EnableAspectJAutoProxy(exposeProxy = true)
public class FrameworkConfig {

    @Bean
    @ConditionalOnMissingBean
    public LogService logService() {
        return new SimpleLogServiceImpl();
    }
}
