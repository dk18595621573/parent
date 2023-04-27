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

    /**
     * 客户（买家）编码.
     */
    private String custId;

    /**
     * 店铺编码(供应商).
     */
    private String shopNo;

    /**
     * 支付方式 线上：5、测试：3.
     */
    private String payMode;

}
