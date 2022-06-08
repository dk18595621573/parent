package com.cloud.framework.lock.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于分布式锁的key.
 *
 * @author breggor
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface XKey {

    /**
     * 使用spel:自定义业务key.
     *
     * @return 自定义业务key
     */
    String value() default "";
}
