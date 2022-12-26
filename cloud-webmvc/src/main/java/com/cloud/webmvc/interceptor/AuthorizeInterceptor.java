package com.cloud.webmvc.interceptor;

import cn.hutool.core.convert.Convert;
import com.cloud.core.utils.SpringUtils;
import com.cloud.webmvc.annotation.PreAuthorize;
import com.cloud.webmvc.exception.AuthorizationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

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
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            PreAuthorize annotation = method.getAnnotation(PreAuthorize.class);
            if (annotation != null) {
                Object result = SpringUtils.resolveExpression(String.format("#{%s}", annotation.value()));
                if (!Convert.toBool(result, false)) {
                    throw new AuthorizationException("没有权限");
                }
            }
        }
        return true;
    }

}
