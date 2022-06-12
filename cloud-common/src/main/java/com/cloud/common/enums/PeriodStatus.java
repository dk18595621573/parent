package com.cloud.common.enums;

/**
 * 账期类型枚举（1:字典；-1:自定义）
 */
public enum PeriodStatus {
    YES(1L, "字典"),
    NO(-1L, "自定义");


    private final Long code;

    private final String msg;

    private PeriodStatus(Long code, String msg) {
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
