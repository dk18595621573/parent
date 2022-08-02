package com.cloud.tencent.config;

import com.cloud.tencent.properties.CosProperties;
import com.cloud.tencent.properties.TencentProperties;
import com.cloud.tencent.service.CosService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * COS 配置.
 *
 * @author zenghao
 * @date 2022/7/17
 */
@ConditionalOnClass(name = "com.qcloud.cos.COSClient")
@ConditionalOnProperty(prefix = CosProperties.COS_PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties(value = {CosProperties.class})
public class CosConfig {

    /**
     * 创建cos服务.
     *
     * @param cosProperties cos相关配置
     * @return cos服务
     */
    @Bean
    @ConditionalOnMissingBean
    public CosService cosService(final TencentProperties tencentProperties, final CosProperties cosProperties) {
        COSCredentials credentials = new BasicCOSCredentials(tencentProperties.getSecretId(), tencentProperties.getSecretKey());
        ClientConfig clientConfig = new ClientConfig(new Region(cosProperties.getRegion()));
        COSClient cosClient = new COSClient(credentials, clientConfig);
        return new CosService(cosProperties, cosClient);
    }
}
