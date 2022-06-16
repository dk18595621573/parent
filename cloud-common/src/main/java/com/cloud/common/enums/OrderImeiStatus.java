package com.cloud.common.enums;

/**
 * @author dk185
 * 串码要求
 */
public enum OrderImeiStatus {
    /** 发货前提供串码 */
    PRE_DELIVERY(1L, "发货前提供串码"),
    /** 发货后提供串码 */
    AFTER_DELIVERY(2L, "发货后提供串码"),
    /** 不需要提供串码 */
    NO_IMEI(3L, "不需要提供串码");

    private final Long code;
    private final String msg;


    OrderImeiStatus(Long code, String msg) {
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
