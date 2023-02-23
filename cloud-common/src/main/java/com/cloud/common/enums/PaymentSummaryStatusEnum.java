package com.cloud.common.enums;

/**
 * 付款汇总状态枚举
 */
public enum PaymentSummaryStatusEnum implements BaseEnum {
    UN_REACH(10, "未开票"),
    SOME_REACH(20, "部分到票"),
    FINISH(100, "核验成功");

    Integer code;

    String msg;

    PaymentSummaryStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    @Override
    public Integer getCode() {
        return null;
    }

    @Override
    public String getMsg() {
        return null;
    }
}
