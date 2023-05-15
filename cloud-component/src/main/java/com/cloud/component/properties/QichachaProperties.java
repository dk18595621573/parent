package com.cloud.component.properties;

import com.cloud.common.constant.Constants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 企查查配置属性
 * @author m
 */
@Data
@ConfigurationProperties(prefix = QichachaProperties.QICHACHA_PREFIX)
public class QichachaProperties {

    public static final String QICHACHA_PREFIX = Constants.CONFIG_PREFIX + "qichacha";


    /**
     * 密钥
     */
    private String appkey;

    /**
     * 加密
     */
    private String secretKey;


}
