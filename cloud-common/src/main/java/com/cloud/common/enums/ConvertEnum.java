package com.cloud.common.enums;


/**
 * liyanhao
 * @param <E>
 */
public interface ConvertEnum<E extends ConvertEnum> {
    abstract Integer getValue();
    abstract String getDesc();
}
