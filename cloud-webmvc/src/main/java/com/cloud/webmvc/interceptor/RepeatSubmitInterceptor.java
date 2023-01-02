package com.cloud.webmvc.interceptor;

import com.cloud.common.core.model.AjaxResult;
import com.cloud.common.utils.json.JsonUtil;
import com.cloud.webmvc.annotation.RepeatSubmit;
import com.cloud.webmvc.utils.ServletUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 防止重复提交拦截器
 *
 * @author author
 */
public abstract class RepeatSubmitInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        Method method = ((HandlerMethod) handler).getMethod();
        RepeatSubmit annotation = AnnotationUtils.findAnnotation(method, RepeatSubmit.class);
        if (annotation != null) {
            if (this.isRepeatSubmit(request, annotation)) {
                AjaxResult ajaxResult = AjaxResult.error(annotation.message());
                ServletUtils.renderString(response, JsonUtil.toJson(ajaxResult));
                return false;
            }
        }
        return true;
    }

    /**
     * 验证是否重复提交由子类实现具体的防重复提交的规则
     *
     * @param request
     * @return
     * @throws Exception
     */
    public abstract boolean isRepeatSubmit(HttpServletRequest request, RepeatSubmit annotation);
}
