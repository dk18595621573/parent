package com.cloud.webmvc.utils;

import com.cloud.common.constant.HttpStatus;
import com.cloud.common.core.model.RequestUser;
import com.cloud.common.exception.ServiceException;
import com.cloud.common.threads.RequestThread;

import java.util.Objects;

/**
 * 安全服务工具类
 *
 * @author author
 */
public class SecurityUtils {
    /**
     * 用户ID
     **/
    public static Long getUserId() {
        try {
            return getLoginUser().getUserId();
        } catch (Exception e) {
            throw new ServiceException("获取用户ID异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取部门ID
     **/
    public static Long getDeptId() {
        try {
            return getLoginUser().getDeptId();
        } catch (Exception e) {
            throw new ServiceException("获取部门ID异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取用户账户
     **/
    public static String getUsername() {
        try {
            return getLoginUser().getUsername();
        } catch (Exception e) {
            throw new ServiceException("获取用户账户异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取用户
     **/
    public static RequestUser getLoginUser() {
        RequestUser user = RequestThread.getUser();
        if (Objects.isNull(user)) {
            throw new ServiceException("获取用户信息异常", HttpStatus.UNAUTHORIZED);
        }
        return user;
    }

}
