package com.cloud.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 消息中心相关常量
 *
 * @author lifei
 * @date 2023/4/11 14:48
 * @description
 */
public class MsgCenterConstants {

    /**
     * 消息分类编码
     */
    @Getter
    @AllArgsConstructor
    public enum MessageTypeCode {
        ORDER("order", "订单"),
        FINANCE("finance", "财务"),
        AUDIT("audit", "审核"),
        REFUND("refund", "售后"),
        OTHER("other", "其他"),

        ;

        private final String code;
        private final String desc;
    }

    /**
     * 消息接收人类型
     */
    @Getter
    @AllArgsConstructor
    public enum ReceiverType {
        COMPANY(1, "公司"),
        USER(2, "用户"),

        ;
        private final int code;
        private final String desc;
    }

    /**
     * 操作反馈类型
     */
    @Getter
    @AllArgsConstructor
    public enum FeedbackType {
        NO_FEEDBACK(0, "无反馈"),
        IN_SITE_JUMP(1, "站内跳转"),
        IN_SITE_POP(2,"站内弹窗")

        ;
        private final int code;
        private final String desc;
    }

    /**
     * 推送方式
     */
    @Getter
    @AllArgsConstructor
    public enum PushType {
        IN_SITE_MAIL(1,"站内信"),
        SHORT_MESSAGE(2,"短信"),

        ;

        private final int code;
        private final String desc;
    }

}
