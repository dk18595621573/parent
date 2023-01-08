package com.cloud.dal.aspect;

import cn.hutool.core.collection.CollUtil;
import com.cloud.dal.annotation.DataScope;
import com.cloud.common.constant.Constants;
import com.cloud.common.core.model.RequestUser;
import com.cloud.common.threads.RequestThread;
import com.cloud.common.utils.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 数据过滤处理
 *
 * @author author
 */
@Aspect
@Component
public class DataScopeAspect {

    /**
     * 数据权限过滤关键字
     */
    public static final String DATA_SCOPE = "dataScope";

    @Before("@annotation(dataScope)")
    public void doBefore(JoinPoint point, DataScope dataScope) {
        // 获取当前的用户
        RequestUser loginUser = RequestThread.getUser();
        // 如果是超级管理员，则不过滤数据
        if (StringUtils.isNotNull(loginUser) &&
            (CollUtil.isNotEmpty(loginUser.getRoles()) &&
                loginUser.getRoles().stream().noneMatch(r -> r.getRoleId().equals(Constants.ROLE_ADMIN_ID)))) {
            dataScopeFilter(point, loginUser, dataScope.value());
        }
        RequestThread.addParam(DATA_SCOPE, "");
    }

    @After("@annotation(dataScope)")
    public void doAfter(JoinPoint point, DataScope dataScope) {
        RequestThread.removeParam(DATA_SCOPE);
    }

    /**
     * 数据范围过滤
     *
     * @param joinPoint 切点
     * @param user      用户
     * @param alias     别名
     */
    public void dataScopeFilter(JoinPoint joinPoint, RequestUser user, String alias) {

    }

}
