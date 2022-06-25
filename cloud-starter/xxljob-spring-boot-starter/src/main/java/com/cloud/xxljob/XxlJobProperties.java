package com.cloud.xxljob;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * xxljob相关配置.
 * @author breggor
 */
@Data
@ConfigurationProperties(prefix = XxlJobProperties.PREFIX)
public class XxlJobProperties {
    public static final String PREFIX = "xxl.job";

    private AdminInfo admin;

    private String accessToken;

    private ExecutorInfo executor;

    /**
     * 服务地址信息.
     */
    @Data
    public static class AdminInfo {

        private String addresses;
    }

    /**
     * 执行器信息.
     */
    @Data
    public static class ExecutorInfo {

        private String appname;

        private String address;

        private String ip;

        private int port;

        private String logPath;

        private int logRetentionDays;
    }
}
