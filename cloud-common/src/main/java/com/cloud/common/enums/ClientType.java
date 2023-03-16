package com.cloud.common.enums;

import java.util.Objects;

/**
 * 客户端类型枚举.
 *
 * @author lingxiang
 */
public enum ClientType {

    /**
     * web端.
     */
    WEB(1, "web端"),
    /**
     * 小程序.
     */
    WECHAT_APPLET(2, "微信小程序端");

    private final Integer code;

    private final String msg;

    ClientType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ClientType fromCode(Integer code) {
        if (Objects.nonNull(code)) {
            for (ClientType clientType : ClientType.values()) {
                if (clientType.getCode().equals(code)) {
                    return clientType;
                }
            }
        }
        return null;
    }
}
