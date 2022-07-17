package com.cloud.tencent;

import com.cloud.tencent.config.CosProperties;
import com.cloud.tencent.config.SmsProperties;
import com.cloud.tencent.config.TencentProperties;
import com.cloud.tencent.service.CosService;
import com.cloud.tencent.service.SmsService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * cos服务自动配置.
 * @author zenghao
 */
@Configuration
@EnableConfigurationProperties(value = {TencentProperties.class, CosProperties.class, SmsProperties.class})
public class TencentAutoConfiguration {

    /**
     * 创建COS客户端bean.
     *
     * @return cos客户端bean
     */
    @Bean
    @ConditionalOnProperty(prefix = CosProperties.COS_PREFIX, name = "enabled", havingValue = "true")
    public COSClient cosClient(final TencentProperties tencentProperties) {
        COSCredentials credentials = new BasicCOSCredentials(tencentProperties.getSecretId(), tencentProperties.getSecretKey());
        ClientConfig clientConfig = new ClientConfig(new Region(tencentProperties.getRegion()));
        return new COSClient(credentials, clientConfig);
    }

    /**
     * 创建cos服务.
     *
     * @param cosClient     cos客户端
     * @param cosProperties cos相关配置
     * @return cos服务
     */
    @Bean
    @ConditionalOnBean(COSClient.class)
    @ConditionalOnMissingBean
    public CosService cosService(final COSClient cosClient, final CosProperties cosProperties) {
        return new CosService(cosProperties, cosClient);
    }

    /**
     * 创建全局 腾讯云配置.
     *
     * @return Credential
     */
    @Bean
    @ConditionalOnProperty(prefix = SmsProperties.SMS_PREFIX, name = "enabled", havingValue = "true")
    public Credential credential(final TencentProperties tencentProperties) {
        return new Credential(tencentProperties.getSecretId(), tencentProperties.getSecretKey());
    }

    /**
     * 创建全局 腾讯云短信配置.
     *
     * @param tencentProperties 腾讯云配置
     * @param credential 腾讯云配置
     * @param smsProperties 短信相关配置
     * @return SmsClient
     */
    @Bean
    @ConditionalOnBean(Credential.class)
    @ConditionalOnMissingBean
    public SmsService smsService(final TencentProperties tencentProperties, final Credential credential, final SmsProperties smsProperties) {
        HttpProfile httpProfile = new HttpProfile();
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setSignMethod(ClientProfile.SIGN_SHA256);
        clientProfile.setHttpProfile(httpProfile);
        SmsClient smsClient = new SmsClient(credential, tencentProperties.getRegion(), clientProfile);

        return new SmsService(smsClient, smsProperties);
    }
}
