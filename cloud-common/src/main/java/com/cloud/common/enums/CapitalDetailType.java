package com.cloud.common.enums;

/**
 * @author mft
 * <p>
 * 保证金数据来源
 */

public enum CapitalDetailType {

    /**
     * 数据来源（0.充值 1.抢单 2.串码 3.取消订单 4.物流补贴 5.撤销订单 6.毁单 7.退货追单 8.物流异常 9.接龙抢单）
     */
    DEPOSIT(0, "充值"),

    COMPETE_ORDER(1, "抢单"),

    IMEL(2, "串码"),

    CANCEL(3, "取消订单"),

    LOGISTIC(4, "物流补贴"),

    REVOKE(5, "撤销订单"),

    DESTROY(6, "毁单"),

    FOLLOW_UP(7, "退货追单"),

    LOGISTICS_ABNORMAL(8, "物流异常"),

    JOINT_ORDER(9, "接龙抢单");

    private final Integer code;

    private final String msg;

    CapitalDetailType(Integer code, String msg) {
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
