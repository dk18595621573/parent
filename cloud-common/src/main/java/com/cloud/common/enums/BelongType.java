package com.cloud.common.enums;

import java.util.Objects;

/**
 * 订单归属类型
 */
public enum BelongType {
    DEFAULT("DEFAULT", "默认内部系统"),
    HUASHENG("HUASHENG","华盛"),
    GUANGYI("GUANGYI","广移"),
    OTHERS("OTHERS","其他")

    ;

    private String code;

    private String message;

    BelongType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static BelongType fromCode(String code) {
        if (Objects.nonNull(code)) {
            for (BelongType belongType : BelongType.values()) {
                if (belongType.getCode().equals(code)) {
                    return belongType;
                }
            }
        }
        return null;
    }
}
