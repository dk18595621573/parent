package com.cloud.common.enums;

/**
 * 挂单状态 ，描述是否撤销挂单（1: 有效；2:无效）
 */
public enum HangOrderStatus {
    EFFICIENT(1, "有效"),
    INVALID(2, "无效");


    private final Integer code;

    private final String msg;

    private HangOrderStatus(Integer code, String msg) {
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
