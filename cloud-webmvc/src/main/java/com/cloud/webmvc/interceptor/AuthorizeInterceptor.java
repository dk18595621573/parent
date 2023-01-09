package com.cloud.webmvc.interceptor;

import cn.hutool.core.convert.Convert;
import com.cloud.core.utils.SpringUtils;
import com.cloud.webmvc.annotation.PreAuthorize;
import com.cloud.webmvc.annotation.SkipAuth;
import com.cloud.webmvc.exception.AuthorizationException;
import lombok.extern.slf4j.Slf4j;
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
 *
 * @author zenghao
 * @date 2022/12/20
 */
@Slf4j
@Component
public class AuthorizeInterceptor implements HandlerInterceptor {

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

        PreAuthorize authorize = AnnotationUtils.findAnnotation(method, PreAuthorize.class);
        if (Objects.nonNull(authorize)) {
            Object result = SpringUtils.resolveExpression(String.format("#{%s}", authorize.value()));
            if (!Convert.toBool(result, false)) {
                throw new AuthorizationException("权限不足，请联系管理员");
            }
        }
        return true;
    }

}
