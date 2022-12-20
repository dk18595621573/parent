package com.cloud.webmvc.aspect;

import cn.hutool.core.convert.Convert;
import com.cloud.core.utils.SpringUtils;
import com.cloud.webmvc.annotation.PreAuthorize;
import com.cloud.webmvc.exception.AuthorizationException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 权限校验处理.
 *
 * @author zenghao
 * @date 2022/12/20
 */
@Aspect
@Slf4j
@Component
public class AuthorizeAspect {

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @Before(value = "@annotation(preAuth)")
    public void doBefore(JoinPoint joinPoint, PreAuthorize preAuth) {
        Object result = SpringUtils.resolveExpression(String.format("#{%s}", preAuth.value()));
        if (!Convert.toBool(result, false)) {
            throw new AuthorizationException("没有权限");
        }
    }

}
