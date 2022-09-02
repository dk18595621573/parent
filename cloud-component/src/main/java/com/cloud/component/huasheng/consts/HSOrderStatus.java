package com.cloud.component.huasheng.consts;


/**
 * 华盛订单状态
 */
public interface HSOrderStatus {

    /**
     * 已发货
     */
    String SHIPPED = "05";

    /**
     * 已签收
     */
    String COMPLETED = "06";

    /**
     * 退货/退款审核通过
     */
    String IS_AFTER_SALE = "T1";

    /**
     * 拒绝退款/退货
     */
    String NO_AFTER_SALE = "T5";

    /**
     * 卖家确认收货
     */
    String CONFIRM_THE_RECEIPT_OF_GOODS = "T3";
}
