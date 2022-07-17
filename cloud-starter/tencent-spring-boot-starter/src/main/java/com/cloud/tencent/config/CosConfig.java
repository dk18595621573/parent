package com.cloud.tencent.config;

import com.cloud.tencent.properties.CosProperties;
import com.cloud.tencent.properties.TencentProperties;
import com.cloud.tencent.service.CosService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
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
@ConditionalOnProperty(prefix = CosProperties.COS_PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties(value = {TencentProperties.class, CosProperties.class})
public class CosConfig {

    /**
     * 创建COS客户端bean.
     *
     * @return cos客户端bean
     */
    @Bean
    @ConditionalOnClass(name = "com.qcloud.cos.COSClient")
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
}
