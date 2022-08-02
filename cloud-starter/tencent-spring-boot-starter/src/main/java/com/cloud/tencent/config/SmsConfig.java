package com.cloud.tencent.config;

import com.cloud.tencent.properties.SmsProperties;
import com.cloud.tencent.service.SmsService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * .
 *
 * @author zenghao
 * @date 2022/7/17
 */
@ConditionalOnClass(name = "com.tencentcloudapi.common.Credential")
@ConditionalOnProperty(prefix = SmsProperties.SMS_PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties(value = {SmsProperties.class})
public class SmsConfig {

    /**
     * 创建全局 腾讯云短信配置.
     *
     * @param credential 腾讯云配置
     * @param smsProperties 短信相关配置
     * @return SmsClient
     */
    @Bean
    @ConditionalOnMissingBean
    public SmsService smsService(final Credential credential, final SmsProperties smsProperties) {
        HttpProfile httpProfile = new HttpProfile();
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setSignMethod(ClientProfile.SIGN_SHA256);
        clientProfile.setHttpProfile(httpProfile);
        SmsClient smsClient = new SmsClient(credential, smsProperties.getRegion(), clientProfile);

        return new SmsService(smsClient, smsProperties);
    }
}
