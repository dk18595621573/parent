package com.cloud.common.enums;

public enum InvoicePlanStepEnum implements BaseEnum {

    ZERO(0, "未提醒"), ONE(1, "提醒开票"), TWO(2, "提醒核验"), THREE(3, "核验结束");

    Integer code;

    String msg;

    InvoicePlanStepEnum(Integer code, String msg) {
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
