package com.cloud.webmvc.properties;

import com.cloud.common.utils.RedisKeyUtil;
import com.cloud.common.utils.StringUtils;
import lombok.Data;

import java.io.Serializable;

@Data
public class TokenProperties implements Serializable {

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

    /**
     * 是否缓存token数据.
     */
    private boolean cached = true;

    /**
     * 缓存前缀
     */
    private String cachePrefix = "login_tokens";

    public void setCachePrefix(final String cachePrefix) {
        this.cachePrefix = cachePrefix;
        if (!StringUtils.endsWith(cachePrefix, RedisKeyUtil.DELIMITER)) {
            this.cachePrefix = cachePrefix + RedisKeyUtil.DELIMITER;
        }
    }
}