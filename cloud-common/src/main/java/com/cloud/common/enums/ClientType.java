package com.cloud.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 客户端类型枚举.
 *
 * @author lingxiang
 */
@Getter
@AllArgsConstructor
public enum ClientType {

    /**
     * web端.
     */
    WEB(1, "plat-form-web", "WEB端"),

    /**
     * 小程序.
     */
    WECHAT_APPLET(2, "plat-form-applet", "微信小程序端");

    private final Integer code;

    private final String value;

    private final String msg;

    /**
     * 根据code获取.
     *
     * @param code code
     * @return 结果
     */
    public static ClientType getByCode(final Integer code) {
        return Arrays.stream(ClientType.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
    }

    /**
     * 根据value获取.
     *
     * @param value value
     * @return 结果
     */
    public static ClientType getByValue(final String value) {
        return Arrays.stream(ClientType.values()).filter(i -> i.getValue().equals(value)).findFirst().orElse(null);
    }

}
