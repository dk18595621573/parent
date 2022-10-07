package com.cloud.rocketmq.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * rocketmq 自定义配置.
 *
 * @author zenghao
 * @date 2022/9/25
 */
@Data
@ConfigurationProperties(prefix = RocketMQProperties.PREFIX)
public class RocketMQProperties {
    public static final String PREFIX = "rocketmq.custom";

    private String namespace;
}
