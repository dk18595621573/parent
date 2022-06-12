package com.cloud.common.enums;

/**
 * 挂单价格状态 （1: 可出价；2:待确认 3.失效）
 */
public enum HangOrderPriceStatus {

    EFFICIENT(1, "可出价"),
    CONFIRMING(2, "待确认"),
    INVALID(3, "失效");


    private final Integer code;

    private final String msg;

    HangOrderPriceStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
