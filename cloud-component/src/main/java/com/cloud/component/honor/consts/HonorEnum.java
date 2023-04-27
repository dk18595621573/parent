package com.cloud.component.honor.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 荣耀枚举.
 *
 * @author Luo
 * @date 2023-03-07 9:13
 */
public class HonorEnum {

    /**
     * 请求方法枚举.
     */
    @Getter
    @AllArgsConstructor
    public enum Url {

        /**
         * 订单创建接口.
         */
        ORDER_CREATE("/order/create", "订单创建接口"),

        /**
         * 订单查询接口.
         */
        ORDER_QUERY("/order/query", "订单查询接口"),

        /**
         * 出库信息查询.
         */
        ORDER_OUTBOUND("/order/outbound", "出库信息查询"),

        /**
         * 出库串码查询.
         */
        ORDER_OUTBOUND_SN("/order/outboundsn", "出库串码查询"),

        /**
         * 实时物流信息查询.
         */
        LOGISTICS_QUERY("/logistics/query", "实时物流信息查询"),

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
        public static Url getByCode(final String code) {
            return Arrays.stream(Url.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
        }

    }

    /**
     * 响应码.
     */
    @Getter
    @AllArgsConstructor
    public enum ErrCode {

        /**
         * 0000：调用成功.
         */
        CODE_0000("0000", "调用成功"),

        /**
         * 0：调用成功.
         */
        CODE_0("0", "调用成功"),

        ;

        /**
         * code.
         */
        private final String code;

        /**
         * 描述.
         */
        private final String desc;

        /**
         * 是否调用成功.
         *
         * @param code code
         * @return 结果
         */
        public static boolean success(final String code) {
            return Arrays.asList(CODE_0000.getCode(),CODE_0.getCode()).contains(code);
        }

        /**
         * 根据code获取.
         *
         * @param code code
         * @return 结果
         */
        public static ErrCode getByCode(final String code) {
            return Arrays.stream(ErrCode.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
        }

    }


}
