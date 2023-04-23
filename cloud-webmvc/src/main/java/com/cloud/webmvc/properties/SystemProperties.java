package com.cloud.webmvc.properties;

import com.cloud.common.constant.Constants;
import com.cloud.common.utils.StringUtils;
import jodd.util.StringPool;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 读取项目相关配置
 *
 * @author author
 */
@Data
@ConfigurationProperties(prefix = SystemProperties.PREFIX)
public class SystemProperties {

    public static final String PREFIX = Constants.CONFIG_PREFIX + "system";

    /**
     * 模块名
     */
    private String module;

    /**
     * api项目域名
     */
    private String apiDomain;

    /**
     * web域名
     */
    private String webDomain;

    /**
     * 运营平台域名
     */
    private String admDomain;

    /**
     * user项目域名
     */
    private String userDomain;

    /**
     * 获取地址开关
     */
    private boolean addressEnabled;

    /**
     * 验证码类型
     */
    private String captchaType;

    /**
     * token配置
     */
    private TokenProperties token = new TokenProperties();

    /**
     * 权限相关配置
     */
    private SecurityProperties security = new SecurityProperties();

    /**
     * xss配置
     */
    private XssProperties xss = new XssProperties();

    /**
     * 获取项目域名.
     *
     * @return 项目域名
     */
    public String getModuleDomain() {
        if (StringUtils.isBlank(module)) {
            return apiDomain;
        }
        return apiDomain.concat(StringPool.SLASH).concat(module);
    }

}
