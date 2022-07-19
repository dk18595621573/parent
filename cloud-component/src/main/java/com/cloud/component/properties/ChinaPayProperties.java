package com.cloud.component.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * 银联相关配置.
 *
 * @author zenghao
 * @date 2022/6/20
 */
@Data
@ConfigurationProperties(prefix = ChinaPayProperties.CHINAPAY_PREFIX)
public class ChinaPayProperties implements Serializable {

    public static final String CHINAPAY_PREFIX = "cloud.chinapay";
    /**
     * 银联域名
     */
    private String domain;

    /**
     * 银联商户号配置路径
     */
    private String mchConfig;

    /**
     * 银联商户号
     */
    private String mchNo;
}
