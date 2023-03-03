package com.cloud.tencent.config;

import com.cloud.tencent.properties.OcrProperties;
import com.cloud.tencent.properties.SmsProperties;
import com.cloud.tencent.properties.TencentProperties;
import com.cloud.tencent.service.OcrService;
import com.cloud.tencent.service.SmsService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * 腾讯云配置.
 *
 * @author zenghao
 * @date 2023/3/2
 */
@ConditionalOnClass(name = "com.tencentcloudapi.common.Credential")
@Import(value = {TencentCloudConfig.OcrAutoConfig.class, TencentCloudConfig.SmsAutoConfig.class})
public class TencentCloudConfig {

    /**
     * 创建全局 腾讯云配置.
     *
     * @return Credential
     */
    @Bean
    @RefreshScope
    public Credential credential(final TencentProperties tencentProperties) {
        return new Credential(tencentProperties.getSecretId(), tencentProperties.getSecretKey());
    }

    /**
     * OCR自动装配.
     */
    @ConditionalOnProperty(prefix = OcrProperties.OCR_PREFIX, name = "enabled", havingValue = "true")
    @EnableConfigurationProperties(value = {OcrProperties.class})
    public static class OcrAutoConfig {


        /**
         * 创建全局 腾讯云短信配置.
         *
         * @param credential 腾讯云配置
         * @return SmsClient
         */
        @Bean
        @RefreshScope
        @ConditionalOnMissingBean
        public OcrService ocrService(final Credential credential, final OcrProperties ocrProperties) {
            HttpProfile httpProfile = new HttpProfile();
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod(ClientProfile.SIGN_SHA256);
            clientProfile.setHttpProfile(httpProfile);
            OcrClient ocrClient = new OcrClient(credential, ocrProperties.getRegion(), clientProfile);

            return new OcrService(ocrClient);
        }
    }

    /**
     * SMS短信自动装配.
     */
    @ConditionalOnProperty(prefix = SmsProperties.SMS_PREFIX, name = "enabled", havingValue = "true")
    @EnableConfigurationProperties(value = {SmsProperties.class})
    public static class SmsAutoConfig {

        /**
         * 创建全局 腾讯云短信配置.
         *
         * @param credential    腾讯云配置
         * @param smsProperties 短信相关配置
         * @return SmsClient
         */
        @Bean
        @RefreshScope
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
}
