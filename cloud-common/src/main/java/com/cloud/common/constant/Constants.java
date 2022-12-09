package com.cloud.common.constant;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.cloud.common.utils.uuid.ShortSnowflake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通用常量信息
 *
 * @author author
 */
public class Constants {
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

    /**
     * http请求
     */
    public static final String HTTP = "http://";

    /**
     * https请求
     */
    public static final String HTTPS = "https://";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用成功标识
     */
    public static final Integer SUCCESS_I = 0;

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 通用失败标识
     */
    public static final Integer FAIL_I = 1;
    /**
     * sys-user 日志
     */
    public static final Logger SYS_LOG = LoggerFactory.getLogger("sys-user");

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 注册
     */
    public static final String REGISTER = "Register";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 防重提交 redis key
     */
    public static final String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     * 限流 redis key
     */
    public static final String RATE_LIMIT_KEY = "rate_limit:";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;

    /**
     * 令牌
     */
    public static final String TOKEN = "token";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌前缀
     */
    public static final String LOGIN_USER_KEY = "login_user_key";

    public static final String MDC_TRACE_ID = "TRACE";

    public static final String MDC_USER_ID = "USER";

    public static final String MDC_COMPANY_ID = "COMPANY";

//    /**
//     * 用户ID
//     */
//    public static final String JWT_USERID = "userid";
//
//    /**
//     * 用户名称
//     */
//    public static final String JWT_USERNAME = "sub";
//
//    /**
//     * 用户头像
//     */
//    public static final String JWT_AVATAR = "avatar";
//
//    /**
//     * 创建时间
//     */
//    public static final String JWT_CREATED = "created";
//
//    /**
//     * 用户权限
//     */
//    public static final String JWT_AUTHORITIES = "authorities";

    /**
     * 登录用户ID
     */
    public static final String LOGIN_USERID = "userId:";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 所有权限标识
     */
    public static final String ALL_PERMISSION = "*:*:*";

    /**
     * 管理员角色权限标识
     */
    public static final String SUPER_ADMIN = "admin";

    public static final String ROLE_DELIMETER = ",";

    public static final String PERMISSION_DELIMETER = ",";

    /**
     * 雪花算法使用，暂时写死
     */
    public static final Snowflake snowflake = IdUtil.getSnowflake(1, 1);

    /**
     * 短id生成，以秒为单位，每秒生成16位
     */
    public static final ShortSnowflake SHORT_SNOWFLAKE = new ShortSnowflake(1);

    /**
     * 电话手机号正则校验表达式
     */
    public static final String phoneRegex = "^(0\\d{2,3}-\\d{5,9}|1[3-9]\\d{9}|1[3-9]\\d{9}-\\d{4,6})$";

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

    /**
     * 是否为管理员角色
     *
     * @param roleId 角色ID
     * @return 结果
     */
    public static boolean isAdminRole(Long roleId) {
        return roleId != null && 1L == roleId;
    }

}
