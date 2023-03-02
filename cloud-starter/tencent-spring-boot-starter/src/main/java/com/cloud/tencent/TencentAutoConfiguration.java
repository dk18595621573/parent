package com.cloud.tencent;

import com.cloud.tencent.config.CosConfig;
import com.cloud.tencent.config.TencentCloudConfig;
import com.cloud.tencent.properties.TencentProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * cos服务自动配置.
 * @author zenghao
 */
@Configuration
@Import(value = {CosConfig.class, TencentCloudConfig.class})
@EnableConfigurationProperties(TencentProperties.class)
public class TencentAutoConfiguration {

}
