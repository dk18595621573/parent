package com.cloud.tencent.config;

import com.cloud.tencent.properties.ImProperties;
import com.cloud.tencent.service.ImService;
import com.tencentcloudapi.im.ApiClient;
import com.tencentcloudapi.im.Configuration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;

/**
 * 腾讯云 IM 配置.
 *
 * @author Luo
 * @date 2023-3-27 13:48:34
 */
@ConditionalOnProperty(prefix = ImProperties.IM_PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties(value = {ImProperties.class})
public class ImConfig {

    /**
     * 创建IM服务.
     *
     * @param imProperties im相关配置
     * @return im服务
     */
    @Bean
    @RefreshScope
    @ConditionalOnMissingBean
    public ImService imService(final ImProperties imProperties) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath(imProperties.getUrl());
        defaultClient.setSdkappid(imProperties.getSdkAppId());
        defaultClient.setIdentifier(imProperties.getAdminUserId());
        defaultClient.setKey(imProperties.getSecretKey());
        Configuration.setDefaultApiClient(defaultClient);
        return new ImService(imProperties);
    }

}
