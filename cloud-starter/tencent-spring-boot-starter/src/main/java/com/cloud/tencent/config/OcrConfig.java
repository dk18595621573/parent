package com.cloud.tencent.config;

import com.cloud.tencent.properties.OcrProperties;
import com.cloud.tencent.service.OcrService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;

/**
 * OCR自动装配.
 *
 * @author zenghao
 * @date 2022/8/2
 */
@ConditionalOnClass(name = "com.tencentcloudapi.common.Credential")
@ConditionalOnProperty(prefix = OcrProperties.OCR_PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties(value = {OcrProperties.class})
public class OcrConfig {

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
