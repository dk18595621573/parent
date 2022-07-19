package com.cloud.log;

import com.cloud.log.service.IOperatorGetService;
import com.cloud.log.service.ISysLogService;
import com.cloud.log.service.impl.DefaultOperatorGetService;
import com.cloud.log.service.impl.DefaultSysLogService;
import com.cloud.log.aop.LogRecordAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * log starter setting .
 *
 * @author xht.
 * @createTime 2021年12月17日 16:06:00
 */
@Configuration
public class LogStarterConfig {

    @Bean
    public ISysLogService sysLogService(){
        return new DefaultSysLogService();
    }

    @Bean
    public IOperatorGetService operatorGetService(){
        return new DefaultOperatorGetService();
    }

    @Bean
    @ConditionalOnMissingBean
    public LogRecordAspect logRecordAspect() {
        return new LogRecordAspect();
    }
}
