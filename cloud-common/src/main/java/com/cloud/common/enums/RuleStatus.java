package com.cloud.common.enums;


/**
 * 规则状态（1: 有效；2:无效）
 */
public enum RuleStatus {
    EFFICIENT(1, "有效"),
    INVALID(2, "无效"),
    ;


    private final Integer code;

    private final String msg;

    RuleStatus(Integer code, String msg) {
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
