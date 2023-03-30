package com.cloud.webmvc.interceptor;

import cn.hutool.core.convert.Convert;
import com.cloud.common.core.model.RequestUser;
import com.cloud.core.utils.SpringUtils;
import com.cloud.webmvc.annotation.PreAuthorize;
import com.cloud.webmvc.annotation.SkipAuth;
import com.cloud.webmvc.exception.AuthenticationException;
import com.cloud.webmvc.exception.AuthorizationException;
import com.cloud.webmvc.service.TokenService;
import com.cloud.webmvc.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 权限校验处理.
 *  先校验是否已经登录
 *
 * @author zenghao
 * @date 2022/12/20
 */
@Slf4j
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private static final long TWENTY_MINUTE = 20 * 60 * 1000L;

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        Method method = ((HandlerMethod) handler).getMethod();
        SkipAuth skipAuth = AnnotationUtils.findAnnotation(method, SkipAuth.class);
        if (Objects.nonNull(skipAuth)) {
            return true;
        }

        RequestUser loginUser = SecurityUtils.getLoginUser();
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime < currentTime) {
            tokenService.delLoginUser(loginUser.getUserId(), loginUser.getToken());
            throw new AuthorizationException("登录状态已过期，请重新登录");
        }
        // 还剩20分钟时自动续期，仅redis模式适用
        if (expireTime - currentTime <= TWENTY_MINUTE) {
            tokenService.refreshToken(loginUser);
        }
        PreAuthorize authorize = AnnotationUtils.findAnnotation(method, PreAuthorize.class);
        if (Objects.nonNull(authorize)) {
            Object result = SpringUtils.resolveExpression(String.format("#{%s}", authorize.value()));
            if (!Convert.toBool(result, false)) {
                throw new AuthenticationException("权限不足，请联系管理员");
            }
        }
        return true;
    }

}
