package com.cloud.common.enums;

/**
 * 订单子状态（20:新建；21:撤销；22:超时发货；23:超时流拍，30:待抢单 ，31:待确认）
 */
public enum OrderSubStatus {

    /**
     * 没有子状态的时候需要将子状态调整为默认状态
     */
    DEFAULT(0, "默认"),
    NEW(20, "新建"),
    REVOKE(21, "撤销"),
    OVERTIME_DELIVERY(22, "超时发货"),
    OVERTIME_STREAMING(23, "超时流拍"),
    PENDING_ORDER(30, "待抢单"),
    TO_BE_CONFIRMED(31, "待确认"),
    /**
     * 目前需要提交串码
     */
    TO_BE_COMPLETE(40, "待完善发货信息"),
    /**
     * 已提交串码
     */
    COMPLETE_CODE(41, "已完善发货信息"),
    /** 部分发货 */
    PART_SHIPMENTS(42, "部分发货"),

    /**
     * 已发货待提交串码
     */
    SEND_TO_BE_COMPLETE(50, "已发货待提交串码"),
    /**
     * 已发货已提交串码
     */
    SEND_COMPLETE_CODE(51, "已发货已完善发货信息"),
    /** 部分收货 */
    PART_RECEIVING(51, "部分收货"),

    /** 采购入库收货 */
    SEND_PURCHASING(60, "采购入库收货"),
    /** 一件代发收货 */
    SEND_UNDERTAKES_TO(61, "一件代发收货"),
    /**
     * 退货退单
     */
    DEMAND_AFFIRM(70, "需求方发起退单"),
    SUPPLIER_NOT_AFFIRM(71, "供应商未确认"),
    SUPPLIER_THEN_AFFIRM(72, "供应商已确认"),
    WAREHOUSE_NOT_AFFIRM(73, "无仓未确认"),
    WAREHOUSE_THEN_AFFIRM(74, "无仓已确认"),
    WAREHOUSE_THEN_SEND_BACK(75, "无仓已退回"),
    SUPPLIER_THEN_TAKE(76, "供应商已收货"),
    ;

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
