package com.cloud.tencent;

import com.cloud.tencent.config.CosConfig;
import com.cloud.tencent.config.SmsConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * cos服务自动配置.
 * @author zenghao
 */
@Configuration
@Import(value = {CosConfig.class, SmsConfig.class})
public class TencentAutoConfiguration {



}
