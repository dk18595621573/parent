package com.cloud.common.enums;

public enum InvoicePlanStatus implements BaseEnum {

    UN_SUBMIT_INVOICE(60, "未开票"),

    ALREADY_SUBMIT_INVOICE(70, "已经开票"),

    INVOICE_EXCEPTION(110, "发票异常");

    Integer code;

    String msg;

    InvoicePlanStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

}
