package com.cloud.rocketmq.consts;

/**
 * topic 常量.
 *
 * @author zenghao
 * @date 2022/9/23
 */
public interface TopicConstants {

    String DEMO = "TOPIC-DEMO";
    /**
     * 支付结果通知
     */
    String PAY_RESULT = "TOPIC-PAY-RESULT";
    /**
     * 登录.
     */
    String LOGIN = "TOPIC-LOGIN";
    /**
     * 需求方订单状态变更
     */
    String ORDER_STATUS = "TOPIC-ORDER-STATUS";
    /**
     * admin模块消息推送api
     */
    String ADMIN_PUSH_API = "TOPIC-ADMIN-PUSH-API";

    /**
     * 一件代发
     */
    String DROP_SHIPPING = "TOPIC-DROP-SHIPPING";
    /**
     * 批量采购：所有批量采购类型可抢订单
     */
    String COMPETING_ORDER = "TOPIC-COMPETING-ORDER";
    /**
     * 当日抢单：当日抢过的订单
     */
    String TODAY_COMPETE_ORDER = "TOPIC-TODAY-COMPETE-ORDER";
    /**
     * 再次抢单：当日被抢并且待确认的订单
     */
    String ROBBED_CONFIRM_ORDER = "TOPIC-ROBBED-CONFIRM-ORDER";
    /**
     * 接龙订单：所有接龙订单类型可抢订单
     */
    String ORDER_MARKETING = "TOPIC-ORDER-MARKETING";

    /**
     * 询价采购报价中
     */
    String INQUIRY_HANG_ORDER = "TOPIC-INQUIRY-HANG-ORDER";

    /**
     * 小程序消息异常消息推送
     */
    String NEWS_ORDER = "TOPIC-NEWS-ORDER";

    /**
     * 指定企业消息推送
     */
    String COMPANY_MESSAGE_PUSH = "TOPIC-COMPANY-MESSAGE-PUSH";

    /**
     * 消息中心推送
     */
    String MSG_CENTER_PUSH= "TOPIC-MSG-CENTER-MESSAGE-PUSH";

    /**
     * 消息中心批量推送
     */
    String MSG_CENTER_BATCH_PUSH= "TOPIC-MSG-CENTER-BATCH-MESSAGE-PUSH";

    /**
     * fms保存账单消息推送
     */
    String FMS_BILL_PUSH = "TOPIC-FMS-BILL-PUSH";

    /**
     * oms一键抢单埋点
     */
    String OMS_BATCH_COMPETE_POINT = "TOPIC-OMS-BATCH-COMPETE-POINT";

    /**
     * API.
     */
    interface API {

        /**
         * 前缀.
         */
        String PREFIX = "TOPIC-API-";

        /**
         * 影刀通知消息.
         */
        String SHADOW_NOTIFY = PREFIX + "SHADOW-NOTIFY";

    }

    /**
     * 订单.
     */
    interface OMS {

        /**
         * 前缀.
         */
        String PREFIX = "TOPIC-OMS-";

        /**
         * 外部订单消息.
         */
        String EXTERNAL_ORDER = PREFIX + "EXTERNAL-ORDER";

    }

    /**
     * 用户.
     */
    interface USER {

        /**
         * 前缀.
         */
        String PREFIX = "TOPIC-USER-";

        /**
         * 框架合同推送
         */
        String FRAME_CONTRACT_PUSH = PREFIX + "FRAME-CONTRACT-PUSH";

    }

    /**
     * 财务.
     */
    interface FMS {

        /**
         * 前缀.
         */
        String PREFIX = "TOPIC-FMS-";

    }

    /**
     * 系统.
     */
    interface SYSTEM {

        /**
         * 前缀.
         */
        String PREFIX = "TOPIC-SYS-";

    }

    /**
     * 消息.
     */
    interface MESSAGE {

        /**
         * 前缀.
         */
        String PREFIX = "TOPIC-MSG-";

    }

}
