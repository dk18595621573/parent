package com.cloud.common.enums;

/**
 * 是否是最新的挂单（1: 是；2: 否）
 */
public enum NewestStatus {
    YES(1, "是"),
    NO(2, "否");


    private final Integer code;

    private final String msg;

    private NewestStatus(Integer code, String msg) {
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
