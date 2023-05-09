package com.cloud.core;

import com.cloud.core.config.RedisConfig;
import com.cloud.core.log.LogService;
import com.cloud.core.log.SimpleLogServiceImpl;
import com.cloud.core.thread.MdcTaskDecorator;
import com.cloud.core.manager.ShutdownManager;
import com.cloud.core.utils.SpringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.task.TaskExecutorCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.TaskDecorator;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自动注册框架.
 *
 * @author zenghao
 * @date 2022/7/3
 */
@Configuration
// 表示通过aop框架暴露该代理对象,AopContext能够访问
@EnableAspectJAutoProxy(exposeProxy = true)
@Import({RedisConfig.class, SpringUtils.class})
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

    /**
     * mdc日志数据处理
     * @return mdc日志数据处理
     */
    @Bean
    public TaskDecorator taskDecorator() {
        return new MdcTaskDecorator();
    }

    @Bean
    public TaskExecutorCustomizer customizer() {
        return executor -> {
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        };
    }
}
