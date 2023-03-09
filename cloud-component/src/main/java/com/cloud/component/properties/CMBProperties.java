package com.cloud.component.properties;

import com.cloud.common.constant.Constants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 招商银行配置属性.
 *
 * @author nlsm
 * @date 2023-3-9 13:46:01
 */
@Data
@ConfigurationProperties(prefix = CMBProperties.CMB_PREFIX)
public class CMBProperties {

    public static final String CMB_PREFIX = Constants.CONFIG_PREFIX + "cmb";

    /**
     * 用户id.
     */
    private String UID;

    /**
     * 前置机访问地址.
     */
    private String URL;

    /**
     * 业务模式（模式编号）.
     */
    private String busMod;

    /**
     * 银行公钥.
     */
    private String publicKey;

    /**
     * 商户私钥.
     */
    private String privateKey;

    /**
     * 对称秘钥.
     */
    private String aesKey;

}
