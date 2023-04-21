package com.cloud.component.ecss.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * ECSS枚举.
 *
 * @author Luo
 * @date 2023-03-07 9:13
 */
public class ECSSEnum {

    /**
     * 平台类型.
     */
    @Getter
    @AllArgsConstructor
    public enum Type {

        /**
         * 拼多多.
         */
        PIN_DUO_DUO("pinduoduo", "拼多多"),

        /**
         * 天猫.
         */
        T_MALL("tmall", "天猫"),

        ;

        /**
         * code.
         */
        private final String code;

        /**
         * 说明.
         */
        private final String explain;

        /**
         * 根据code获取.
         *
         * @param code code
         * @return 结果
         */
        public static Type getByCode(final String code) {
            return Arrays.stream(Type.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
        }

    }

    /**
     * 请求方法枚举.
     */
    @Getter
    @AllArgsConstructor
    public enum Method {

        /**
         * 订单同步接口.
         */
        ORDER_CREATE("ECSSOrderCreate", "订单同步接口"),

        /**
         * 订单查询（获取）接口.
         */
        ORDER_INQUIRY("ECSSOrderInquiry", "订单查询（获取）接口"),

        /**
         * 发货接口.
         */
        DELIVER_GOODS("ECSSDeliverGoods", "发货接口");

        /**
         * code.
         */
        private final String code;

        /**
         * 说明.
         */
        private final String explain;

        /**
         * 根据code获取.
         *
         * @param code code
         * @return 结果
         */
        public static Method getByCode(final String code) {
            return Arrays.stream(Method.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
        }

    }

    /**
     * 响应码.
     */
    @Getter
    @AllArgsConstructor
    public enum RespCode {

        /**
         * 0：处理成功.
         */
        CODE_0(0, "处理成功"),

        /**
         * 1：参数不正确，desc 描述具体参数.
         */
        CODE_1(1, "参数不正确"),

        /**
         * 2：鉴权失败，desc 描述具体原因.
         */
        CODE_2(2, "鉴权失败"),

        /**
         * 99：处理异常，描述异常原因.
         */
        CODE_99(99, "处理异常");

        /**
         * code.
         */
        private final Integer code;

        /**
         * 描述.
         */
        private final String desc;

        /**
         * 根据code获取.
         *
         * @param code code
         * @return 结果
         */
        public static RespCode getByCode(final Integer code) {
            return Arrays.stream(RespCode.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
        }

    }


}
