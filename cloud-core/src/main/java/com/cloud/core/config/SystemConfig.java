package com.cloud.core.config;

import com.cloud.common.utils.RedisKeyUtil;
import com.cloud.common.utils.StringUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * 读取项目相关配置
 *
 * @author author
 */
@Data
@ConfigurationProperties(prefix = "cloud.system")
public class SystemConfig {
    /**
     * 项目名称
     */
    private String name;

    /**
     * 版本
     */
    private String version;

    /**
     * 版权年份
     */
    private String copyrightYear;

    /**
     * 实例演示开关
     */
    private boolean demoEnabled;

    /**
     * swagger文档开关
     */
    private boolean swaggerEnabled;

    /**
     * 本项目域名
     */
    private String domain;

    /**
     * token配置
     */
    private TokenProperties token = new TokenProperties();

    /**
     * 权限相关配置
     */
    private SecurityProperties security = new SecurityProperties();

    /**
     * 获取地址开关
     */
    private static boolean addressEnabled;

    /**
     * 验证码类型
     */
    private static String captchaType;

    public static boolean isAddressEnabled() {
        return addressEnabled;
    }

    public void setAddressEnabled(boolean addressEnabled) {
        SystemConfig.addressEnabled = addressEnabled;
    }

    public static String getCaptchaType() {
        return captchaType;
    }

    public void setCaptchaType(String captchaType) {
        SystemConfig.captchaType = captchaType;
    }

    @Data
    public static class TokenProperties implements Serializable {

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

    @Data
    public static class SecurityProperties implements Serializable {

        /**
         * 允许匿名访问的接口地址.
         */
        private String[] allows;

    }
}
