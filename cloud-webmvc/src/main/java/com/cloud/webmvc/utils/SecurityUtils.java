package com.cloud.webmvc.utils;

import com.cloud.common.constant.Constants;
import com.cloud.common.core.model.RequestUser;
import com.cloud.common.threads.RequestThread;
import com.cloud.webmvc.exception.AuthorizationException;

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
            String userIdStr = ServletUtils.getRequest().getHeader(Constants.MDC_USER_ID);

            return Long.valueOf(userIdStr);
        } catch (Exception e) {
            throw new AuthorizationException("请先登录后再操作");
        }
    }

    /**
     * 获取部门ID
     **/
    public static Long getDeptId() {
        try {
            String deptIdStr = ServletUtils.getRequest().getHeader(Constants.MDC_COMPANY_ID);
            return Long.valueOf(deptIdStr);
        } catch (Exception e) {
            throw new AuthorizationException("请先登录后再操作");
        }
    }

    /**
     * 获取用户
     **/
    public static RequestUser getLoginUser() {
        RequestUser user = RequestThread.getUser();
        if (Objects.isNull(user)) {
            throw new AuthorizationException("请先登录后再操作");
        }
        return user;
    }

}
