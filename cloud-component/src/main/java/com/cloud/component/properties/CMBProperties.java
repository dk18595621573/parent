package com.cloud.component.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *  招商银行配置属性
 */
@Data
@ConfigurationProperties(prefix = CMBProperties.CMB_PREFIX)
public class CMBProperties{

    public static final String CMB_PREFIX = "cloud.cmb";

    /**
     * 用户id
     */
    private String UID;

    /**
     * 前置机访问地址
     */
    private String URL;
}
