package com.cloud.webmvc.utils;

import com.cloud.common.constant.HttpStatus;
import com.cloud.common.core.model.RequestUser;
import com.cloud.common.exception.ServiceException;
import com.cloud.common.threads.RequestThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * 安全服务工具类
 *
 * @author author
 */
public class SecurityUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtils.class);

    /**
     * 用户ID
     **/
    public static Long getUserId() {
        try {
            return getLoginUser().getUserId();
        } catch (Exception e) {
            LOGGER.warn("获取用户ID异常", e);
            throw new ServiceException("请先登录后再操作", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取部门ID
     **/
    public static Long getDeptId() {
        try {
            return getLoginUser().getDeptId();
        } catch (Exception e) {
            LOGGER.warn("获取部门ID异常", e);
            throw new ServiceException("请先登录后再操作", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取用户账户
     **/
    public static String getUsername() {
        try {
            return getLoginUser().getUsername();
        } catch (Exception e) {
            LOGGER.warn("获取用户名异常", e);
            throw new ServiceException("请先登录后再操作", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取用户
     **/
    public static RequestUser getLoginUser() {
        RequestUser user = RequestThread.getUser();
        if (Objects.isNull(user)) {
            LOGGER.warn("获取登录用户信息失败");
            throw new ServiceException("请先登录后再操作", HttpStatus.UNAUTHORIZED);
        }
        return user;
    }

}
