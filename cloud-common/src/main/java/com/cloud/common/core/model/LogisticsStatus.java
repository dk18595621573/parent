package com.cloud.common.core.model;

import cn.hutool.core.util.ArrayUtil;
import lombok.experimental.UtilityClass;

import java.util.Objects;

/**
 * @author nlsm
 * 物流状态处理
 */
@UtilityClass
public class LogisticsStatus {

    /**
     * 签收子状态
     */
    public enum SubState {
        /** 快件已签收 */
        SIGN("3", "快件已签收"),
        /** 本人签收 */
        SELF_SIGN("301", "本人签收"),
        /** 派件异常后签收 */
        ERROR_SIGN("302", "派件异常后签收"),
        /** 代签 */
        HOLOGRAPH_SIGN("303", "代签"),
        /** 投柜或站签收 */
        CAST_ARK_SIGN("304", "投柜或站签收"),
        /** 退签 */
        BACK_TO_SIGN("4", "退签"),
        /** 已销单 */
        ALREADY_PIN_SINGLE("401", "已销单"),
        /** 拒签 */
        VISA_REJECTED("14", "拒签"),
        /** 快件揽件 */
        COLLECT("1", "快件揽件"),
        /** 已下单 */
        COLLECT_PLACE_AN_ORDER("101", "已下单"),
        /** 待揽收 */
        COLLECT_WAIT("102", "待揽收"),
        /** 已揽收 */
        COLLECT_ALREADY("103", "已揽收"),
        /** 在途 */
        ON_THE_WAY("0", "在途"),
        /** 到达派件城市 */
        ON_THE_WAY_TO_CITY("1001", "到达派件城市"),
        /** 快件处于运输过程中 */
        ON_THE_WAY_ARTERY("1002", "干线"),
        /** 快件发往到新的收件地址 */
        ON_THE_WAY_FORWARDED("1003", "转递"),
        /** 派件 */
        DELIVERY("5", "派件"),
        /** 快件已经投递到快递柜或者快递驿站 */
        DELIVERY_THROW_IN("501", "投柜或驿站"),
        /** 快件正处于返回发货人的途中 */
        RETURN("6", "退回"),
        /** 快件转给其他快递公司邮寄 */
        FORWARDED("7", "转投"),
        /** 快件存在疑难 */
        KNOTTY("2", "疑难"),
        /** 快件长时间派件后未签收 */
        KNOTTY_TIMEOUT_NO_SIGN("201", "超时未签收"),
        /** 快件长时间没有派件或签收 */
        KNOTTY_TIMEOUT_NO_UPDATE("202", "超时未更新"),
        /** 收件人发起拒收快递,待发货方确认 */
        KNOTTY_REJECTION("203", "拒收"),
        /** 快件派件时遇到异常情况 */
        KNOTTY_DELIVERY_ERROR("204", "派件异常"),
        /** 快件在快递柜或者驿站长时间未取 */
        KNOTTY_TIMEOUT_NOT_FOUND("205", "柜或驿站超时未取"),
        /** 无法联系到收件人 */
        KNOTTY_CAN_NOT_CONTACT("206", "无法联系"),
        /** 超区 */
        KNOTTY_EXCEED_SCOPE("207", "超出快递公司的服务区范围"),
        /** 快件滞留在网点，没有派送 */
        KNOTTY_RETENTION("208", "滞留"),
        /** 快件破损 */
        KNOTTY_DAMAGED("209", "破损"),
        /** 寄件人申请撤销寄件 */
        KNOTTY_CHARGEBACK("210", "销单")
        ;

        private final String code;
        private final String msg;

        SubState(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        /**
         * 获取状态
         * @return 状态
         */
        public String getCode() {
            return code;
        }

        /**
         * 获取说明
         * @return 说明
         */
        public String getMsg() {
            return msg;
        }

        /**
         * 获取说明
         * @param code 状态
         * @return 说明
         */
        public static LogisticsStatus.SubState fromCode(String code) {
            if (Objects.nonNull(code)) {
                for (LogisticsStatus.SubState status : LogisticsStatus.SubState.values()) {
                    if (status.getCode().equals(code)) {
                        return status;
                    }
                }
            }
            return null;
        }
    }

    /** 签收状态集合 */
    public final static String[] SIGN_ARRAY = {SubState.SIGN.code, SubState.SELF_SIGN.code, SubState.ERROR_SIGN.code, SubState.HOLOGRAPH_SIGN.code, SubState.CAST_ARK_SIGN.code};
    /** 拒签状态集合 */
    public final static String[] VISA_ARRAY = {SubState.ALREADY_PIN_SINGLE.code, SubState.BACK_TO_SIGN.code, SubState.VISA_REJECTED.code};
    /** 揽收状态集合 */
    public final static String[] COLLECT_ARRAY = {SubState.COLLECT.code, SubState.COLLECT_PLACE_AN_ORDER.code, SubState.COLLECT_WAIT.code, SubState.COLLECT_ALREADY.code};
    /** 在途状态集合 */
    public final static String[] ON_THE_WAY_ARRAY = {SubState.ON_THE_WAY.code, SubState.ON_THE_WAY_TO_CITY.code, SubState.ON_THE_WAY_ARTERY.code, SubState.ON_THE_WAY_FORWARDED.code};
    /** 派件状态集合 */
    public final static String[] DELIVERY_ARRAY = {SubState.DELIVERY.code, SubState.DELIVERY_THROW_IN.code};
    /** 疑难状态集合 */
    public final static String[] KNOTTY_ARRAY = {SubState.KNOTTY.code, SubState.KNOTTY_TIMEOUT_NO_SIGN.code, SubState.KNOTTY_TIMEOUT_NO_UPDATE.code, SubState.KNOTTY_REJECTION.code, SubState.KNOTTY_DELIVERY_ERROR.code,
                                                    SubState.KNOTTY_TIMEOUT_NOT_FOUND.code, SubState.KNOTTY_CAN_NOT_CONTACT.code, SubState.KNOTTY_EXCEED_SCOPE.code, SubState.KNOTTY_RETENTION.code,
                                                    SubState.KNOTTY_DAMAGED.code, SubState.KNOTTY_CHARGEBACK.code};

    /**
     * 判断是否签收
     * @param state 状态
     * @return true:已签收 false:未签收
     */
    public static boolean signed(String state){
        return ArrayUtil.contains(SIGN_ARRAY, state);
    }

    /**
     * 是否拒签
     * @param state 状态
     * @return true:拒签 false:不是拒签
     */
    public static boolean rejected(String state){
        return ArrayUtil.contains(VISA_ARRAY, state);
    }

    /**
     * 是否揽收
     * @param state 状态
     * @return true:揽收 false:未揽收
     */
    public static boolean collected(String state){
        return ArrayUtil.contains(COLLECT_ARRAY, state);
    }

    /**
     * 是否在途
     * @param state 状态
     * @return true:在途 false:不是在途
     */
    public static boolean onTheWay(String state){
        return ArrayUtil.contains(ON_THE_WAY_ARRAY, state);
    }

    /**
     * 是否处于派送状态
     * @param state 状态
     * @return true:是 false:不是
     */
    public static boolean delivery(String state){
        return ArrayUtil.contains(DELIVERY_ARRAY, state);
    }

    /**
     * 是否疑难状态
     * @param state 状态
     * @return true:是 false:不是
     */
    public static boolean knotty(String state){
        return ArrayUtil.contains(KNOTTY_ARRAY, state);
    }

}
