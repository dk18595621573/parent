package com.cloud.common.enums;

/**
 * 交易订单状态.
 *
 * @author zenghao
 * @date 2022/5/19
 */
public enum TradeOrderStatus implements BaseEnum {

    CONFIRMING(1, "待确认"),
    INVALID(2, "已失效"),
    CLOSED(3, "已成交"),
    ROBBED(4, "被抢走"),
    ;


    private final Integer code;

    private final String msg;

    TradeOrderStatus(Integer code, String msg) {
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
