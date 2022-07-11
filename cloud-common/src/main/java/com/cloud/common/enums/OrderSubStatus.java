package com.cloud.common.enums;

/**
 * 订单子状态（20:新建；21:撤销；22:超时发货；23:超时流拍，30:待抢单 ，31:待确认）
 */
public enum OrderSubStatus {

    /**
     * 没有子状态的时候需要将子状态调整为默认状态
     */
    DEFAULT(0, "默认"),
    /** 新建 */
    NEW(20, "新建"),
    /** 撤销 */
    REVOKE(21, "撤销"),
    /** 超时发货 */
    OVERTIME_DELIVERY(22, "超时发货"),
    /** 超时流拍 */
    OVERTIME_STREAMING(23, "超时流拍"),
    /** 售后-取消订单 */
    AFTER_CANCEL(24, "售后-取消订单"),

    /** 待抢单 */
    PENDING_ORDER(30, "待抢单"),
    /** 待确认 */
    TO_BE_CONFIRMED(31, "待确认"),

    /**
     * 目前需要提交串码
     */
    TO_BE_COMPLETE(40, "待完善发货信息"),
    /**
     * 已提交串码
     */
    COMPLETE_CODE(41, "已完善发货信息"),
    /** 已填写快递信息，还未填写串码 */
    PART_SHIPMENTS(42, "已操作，未填写串码"),
    /** 部分收货 */
    PART_RECEIVING(43, "未发货,部分收货"),
    /** 售后-重新发货 */
    AFTER_SHIPMENTS(44, "售后-重新发货"),
    /** 一件代发收货,待发货 */
    COLLECT_UNDERTAKES_TO(45, "一件代发收货,待发货"),

    /**
     * 已发货待提交串码
     */
    SEND_TO_BE_COMPLETE(50, "已发货待提交串码"),
    /**
     * 已发货已提交串码
     */
    SEND_COMPLETE_CODE(51, "已发货已完善发货信息"),
    /** 一件代发无仓发货 */
    SEND_UNDERTAKES_TO(53, "一件代发已发货"),
    /** 一件代发快递发货 */
    SEND_EXPRESS_TO(54, "一件代发已发货"),
    /** 已发货,部分收货 */
    SEND_COLLECT_PORTION(55,"已发货,部分收货"),

    /** 采购入库收货 */
    SEND_PURCHASING(60, "采购入库收货"),
    /** 快递发货 */
    EXPRESS_DELIVERY(61, "快递发货"),

    /**
     * 退货退单
     */
    DEMAND_AFFIRM(70, "需求方发起退单"),
    SUPPLIER_NOT_AFFIRM(71, "供应商未确认"),
    /** 供应商已确认:普通快递到这结束、无仓收货需要再次流转 */
    SUPPLIER_THEN_AFFIRM(72, "供应商已确认:普通快递到这结束、无仓收货需要再次流转"),
    /** 无仓退换供应商 */
    WAREHOUSE_NOT_AFFIRM(73, "无仓退换供应商"),
    /** 供应商已收 */
    WAREHOUSE_THEN_AFFIRM(74, "供应商已收"),
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
