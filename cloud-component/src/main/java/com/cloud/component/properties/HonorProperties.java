package com.cloud.component.properties;

import com.cloud.common.constant.Constants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 荣耀配置属性.
 *
 * @author Luo
 * @date 2023-3-17 13:14:20
 */
@Data
@ConfigurationProperties(prefix = HonorProperties.HONOR_PREFIX)
public class HonorProperties {

    public static final String HONOR_PREFIX = Constants.CONFIG_PREFIX + "honor";

    /**
     * 接口请求地址.
     */
    private String url;

    /**
     * id.
     */
    private String id;

    /**
     * appKey.
     */
    private String appKey;

}
