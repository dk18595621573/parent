package com.cloud.webmvc.annotation;

import com.cloud.common.constant.Constants;
import com.cloud.common.enums.LimitType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 限流注解
 *
 * @author author
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
    /**
     * 限流key
     */
    String key() default Constants.RATE_LIMIT_KEY;

    /**
     * 限流时间,单位秒
     */
    int time() default 60;

    /**
     * 限流次数
     */
    int count() default 100;

    /**
     * 限流错误描述
     */
    String errMsg() default "访问过于频繁，请稍候再试";

    /**
     * 限流类型
     */
    LimitType limitType() default LimitType.DEFAULT;
}
