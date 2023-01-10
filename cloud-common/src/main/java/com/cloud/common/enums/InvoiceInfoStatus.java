package com.cloud.common.enums;

public enum InvoiceInfoStatus implements BaseEnum {

    WAIT_VERIFIED(10, "待核验"),

    VERIFY_SUCCESS(20, "核验成功"),

    VERIFY_FAILED(30, "核验失败");

    private final Integer code;
    private final String msg;

    InvoiceInfoStatus(Integer code, String msg) {
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
