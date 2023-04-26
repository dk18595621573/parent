package com.cloud.webmvc.interceptor;

import cn.hutool.core.convert.Convert;
import com.cloud.common.core.model.RequestUser;
import com.cloud.common.threads.RequestThread;
import com.cloud.core.utils.SpringUtils;
import com.cloud.webmvc.annotation.PreAuthorize;
import com.cloud.webmvc.annotation.SkipAuth;
import com.cloud.webmvc.exception.AuthenticationException;
import com.cloud.webmvc.exception.AuthorizationException;
import com.cloud.webmvc.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

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

        RequestUser user = tokenService.getLoginUser(request);
        if (Objects.isNull(user)) {
            throw new AuthorizationException("登录状态已失效，请先登录");
        }

        RequestThread.setUser(user);
        PreAuthorize authorize = AnnotationUtils.findAnnotation(method, PreAuthorize.class);
        if (Objects.nonNull(authorize)) {
            Object result = SpringUtils.resolveExpression(String.format("#{%s}", authorize.value()));
            if (!Convert.toBool(result, false)) {
                throw new AuthenticationException("权限不足，请联系管理员");
            }
        }
        return true;
    }

    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView) throws Exception {
        RequestThread.clear();
    }
}
