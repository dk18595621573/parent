package com.cloud.xxljob;

import com.cloud.xxljob.aspect.JobTraceAspect;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 * xxljob自动配置.
 * @author zenghao
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(XxlJobProperties.class)
public class XxlJobAutoConfiguration {

    /**
     * xxljob执行器.
     * @param properties xxljob配置
     * @return xxljob执行器
     */
    @Bean
    public XxlJobSpringExecutor xxlJobExecutor(final XxlJobProperties properties) {
        log.info(">>>>>>>>>>> xxl-job config init.");
        Objects.requireNonNull(properties, "xxl-job: 配置不能为空");

        XxlJobSpringExecutor xxlJobSpringExecutor;
        xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAccessToken(properties.getAccessToken());
        xxlJobSpringExecutor.setAdminAddresses(properties.getAdmin().getAddresses());
        xxlJobSpringExecutor.setAppname(properties.getExecutor().getAppname());
        xxlJobSpringExecutor.setAddress(properties.getExecutor().getAddress());
        xxlJobSpringExecutor.setIp(properties.getExecutor().getIp());
        xxlJobSpringExecutor.setPort(properties.getExecutor().getPort());
        xxlJobSpringExecutor.setLogPath(properties.getExecutor().getLogPath());
        xxlJobSpringExecutor.setLogRetentionDays(properties.getExecutor().getLogRetentionDays());
        return xxlJobSpringExecutor;
    }

    /**
     * 调度日志拦截.
     *
     * @return 调度日志拦截
     */
    @Bean
    public JobTraceAspect jobTraceAspect() {
        return new JobTraceAspect();
    }

}
