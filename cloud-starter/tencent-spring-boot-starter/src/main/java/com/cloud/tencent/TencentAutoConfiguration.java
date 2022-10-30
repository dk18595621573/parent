package com.cloud.tencent;

import com.cloud.tencent.config.CosConfig;
import com.cloud.tencent.config.OcrConfig;
import com.cloud.tencent.config.SmsConfig;
import com.cloud.tencent.properties.TencentProperties;
import com.tencentcloudapi.common.Credential;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * cos服务自动配置.
 * @author zenghao
 */
@Configuration
@Import(value = {CosConfig.class, SmsConfig.class, OcrConfig.class})
@EnableConfigurationProperties(TencentProperties.class)
public class TencentAutoConfiguration {

    /**
     * 创建全局 腾讯云配置.
     *
     * @return Credential
     */
    @Bean
    @RefreshScope
    @ConditionalOnClass(name = "com.tencentcloudapi.common.Credential")
    public Credential credential(final TencentProperties tencentProperties) {
        return new Credential(tencentProperties.getSecretId(), tencentProperties.getSecretKey());
    }

}
