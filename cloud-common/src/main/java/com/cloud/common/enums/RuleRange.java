package com.cloud.common.enums;

/**
 * 规则范围
 */
public enum RuleRange {
    SAME_TYPE__ORDER(1L, "同类型订单"),
    ONLY_THIS(2L, "仅此订单"),
    ALL_TYPES(3L, "所有类型");


    private final Long code;

    private final String msg;

    private RuleRange(Long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Long getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
