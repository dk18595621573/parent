package com.cloud.common.enums;

import java.util.Objects;

/**
 * 订单主状态 1: 新建采购；2: 待发布；3:报价中；4:待发货；5: 已发货；6:已签收
 */
public enum OrderStatus implements BaseEnum {
    NOT_QUOTED(1, "新建采购"),
    PENDING_SUBMISSION(2, "待发布"),
    IN_QUOTATION(3, "报价中"),
    TO_BE_DELIVERED(4, "待发货"),
    SHIPPED(5, "已发货"),
    COMPLETED(6, "已收货"),
    AFTER_SALE(7, "退货退单"),
    ADDRESS_TO_BE_COMPLETED(8, "待补全地址"),
    IMEI_ABNORMAL(9, "串码异常"),
    AFTERMARKET(10, "售后"),
    ON_THE_WAY(11, "在途"),// 前端status传11，后端查询5并且on_the_way为true的订单
    LOGISTICS_ABNORMAL(12, "物流异常"),
    REVOKE(13, "撤销"),
    NOT_RECOVERED(14, "未追回订单"),
    AUCTION_OFFER(15, "竞拍出价"),
    PENDING_PROCESSING(16,"待处理"),
    REMOVE(17,"已撤销")
    ;


    private final Integer code;

    private final String msg;

    OrderStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static OrderStatus fromCode(Integer code) {
        if (Objects.nonNull(code)) {
            for (OrderStatus status : OrderStatus.values()) {
                if (status.getCode().equals(code)) {
                    return status;
                }
            }
        }
        return null;
    }
}
