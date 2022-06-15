package com.cloud.common.enums;

/**
 * @author dk185
 * 串码要求
 */
public enum OrderImeiStatus {
    /** 发货前提供串码 */
    PRE_DELIVERY(1, "发货前提供串码"),
    /** 发货后提供串码 */
    AFTER_DELIVERY(2, "发货后提供串码"),
    /** 不需要提供串码 */
    NO_IMEI(3, "不需要提供串码");

    private final Integer code;
    private final String msg;


    OrderImeiStatus(Integer code, String msg) {
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