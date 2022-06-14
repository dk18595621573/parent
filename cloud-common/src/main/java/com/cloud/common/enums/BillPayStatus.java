package com.cloud.common.enums;

/**
 * @author dk185
 * 账单支付状态
 */

public enum BillPayStatus {
    /** 未付款 */
    NO_PAY(1, "未付款"),
    /** 已付款待确认 */
    UNCONFIRMED(2, "已付款待确认"),
    /** 已确认 */
    CONFIRMED(3, "已确认");

    private final Integer code;
    private final String msg;

    BillPayStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer getCode(){
        return code;
    }

    private String getMsg(){
        return msg;
    }
}
