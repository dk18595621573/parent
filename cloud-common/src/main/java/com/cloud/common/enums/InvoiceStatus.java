package com.cloud.common.enums;

public enum InvoiceStatus implements BaseEnum {

    NOT_SUBMIT(10, "未到票"),

    PART_SUBMIT(20, "部分到票"),

    TO_BE_VERIFIED(30, "待核验"),

    VERIFY_SUCCESS(40, "核验成功"),

    VERIFY_FAILED(50, "核验失败");

    private final Integer code;
    private final String msg;

    InvoiceStatus(Integer code, String msg) {
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
