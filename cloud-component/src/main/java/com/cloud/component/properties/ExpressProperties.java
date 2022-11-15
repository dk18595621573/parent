package com.cloud.component.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 快递信息查询条件.
 *
 * @author zenghao
 * @date 2022/7/19
 */
@Data
@ConfigurationProperties(prefix = ExpressProperties.EXPRESS_PREFIX)
public class ExpressProperties {

    public static final String EXPRESS_PREFIX = "cloud.express";


    private String url;

    private String callbackUrl;

    private String key;

    private String customer;
}
