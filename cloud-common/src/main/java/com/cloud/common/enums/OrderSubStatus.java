package com.cloud.common.enums;

/**
 *  订单子状态（20:新建；21:撤销；22:超时发货；23:超时流拍，30:待抢单 ，31:待确认）
 */
public enum OrderSubStatus {

    /**
     * 没有子状态的时候需要将子状态调整为默认状态
     */
    DEFAULT(0, "默认"),
    NEW(20,"新建"),
    REVOKE(21,"撤销"),
    OVERTIME_DELIVERY(22,"超时发货"),
    OVERTIME_STREAMING(23,"超时流拍"),
    PENDING_ORDER(30,"待抢单"),
    TO_BE_CONFIRMED(31,"待确认"),
    /**
     * 目前需要提交串码
     */
    TO_BE_COMPLETE(40,"待完善发货信息"),
    /**
     * 已提交串码
     */
    COMPLETE_CODE(41, "已完善发货信息");

    private final Integer code;

    private final String msg;

    private OrderSubStatus(Integer code, String msg) {
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
