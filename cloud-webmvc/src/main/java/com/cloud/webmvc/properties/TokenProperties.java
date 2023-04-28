package com.cloud.webmvc.properties;

import com.cloud.common.constant.Constants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

@Data
@ConfigurationProperties(prefix = TokenProperties.PREFIX)
public class TokenProperties implements Serializable {

    public static final String PREFIX = Constants.CONFIG_PREFIX + "token";

    /**
     * 令牌自定义标识
     */
    private String header = "Authorization";

    /**
     * 令牌秘钥.
     */
    private String secret = "abcdefghijklmnopqrstuvwxyz";

    /**
     * 令牌有效期（默认30分钟）.
     */
    private int expireTime = 30;

}