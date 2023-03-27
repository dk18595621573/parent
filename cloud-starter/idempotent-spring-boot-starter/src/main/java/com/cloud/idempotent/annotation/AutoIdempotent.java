package com.cloud.idempotent.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动幂等性处理注解.
 *
 * @author zenghao
 * @date 2023/2/27
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface AutoIdempotent {

    /**
     * 配置模块名.
     *
     * @return 模块名
     */
    String module() ;

    /**
     * 需要做幂等的业务key.
     *
     * @return 返回业务key集合
     */
    String[] keys();

    /**
     * 幂等锁过期时间，单位(秒)。默认5秒.
     * @return long
     */
    long expire() default 5L;

    /**
     * 加锁失败 响应值.
     * @return long
     */
    String message() default "正在处理，请稍后再试";

    /**
     * 返回数据类型.
     * 注意点：返回类型是泛型时：不能是元素类型
     *
     * @return Class
     */
    Class<?> resultType() default Void.class;
}
