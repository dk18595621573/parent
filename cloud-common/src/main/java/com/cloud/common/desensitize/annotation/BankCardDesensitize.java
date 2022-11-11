package com.cloud.common.desensitize.annotation;


import com.cloud.common.desensitize.desensitization.BankCardDesensitization;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.github.annotation.Desensitize;

import java.lang.annotation.*;


/**
 * @author nlsm
 * 银行卡脱敏 注解
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@Desensitize(desensitization = BankCardDesensitization.class)
@Documented
public @interface BankCardDesensitize {
}
