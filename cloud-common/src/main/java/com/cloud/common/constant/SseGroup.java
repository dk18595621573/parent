package com.cloud.common.constant;

/**
 * sse推送事件分组.
 */
public interface SseGroup {
    /**
     * 登录.
     */
    String LOGIN = "login";

    /**
     * 订单状态变更
     */
    String ORDER_STATUS = "order_status";

    /**
     * 支付结果通知
     */
    String PAY_RESULT = "pay_result";

    /**
     * 供应商订单分组
     */
    interface AgentOrderGroup {

        /**
         * 一件代发
         */
        String DROP_SHIPPING = "drop_shipping";
        /**
         * 批量采购：所有批量采购类型可抢订单
         */
        String COMPETING_ORDER = "competing_order";
        /**
         * 当日抢单：当日抢过的订单
         */
        String TODAY_COMPETE_ORDER = "today_compete_order";
        /**
         * 再次抢单：当日被抢并且待确认的订单
         */
        String ROBBED_CONFIRM_ORDER = "robbed_confirm_order";

    }

}