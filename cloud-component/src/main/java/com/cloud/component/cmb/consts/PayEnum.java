package com.cloud.component.cmb.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 支付相关枚举类.
 *
 * @author Luo
 * @date 2023-03-08 11:17
 */
public class PayEnum {

    /**
     * 请求状态.
     */
    @Getter
    @AllArgsConstructor
    public enum ReqSts {

        /**
         * AUT：等待审批.
         */
        AUT(1, "AUT", "等待审批"),

        /**
         * NTE：终审完毕.
         */
        NTE(2, "NTE", "终审完毕"),

        /**
         * BNK：银行处理中/可疑.
         * 表示状态未知，需要人工介入处理
         */
        BNK(3, "BNK", "银行处理中"),

        /**
         * WRF：银行处理中.
         */
        WRF(4, "WRF", "银行处理中"),

        /**
         * FIN：完成.
         */
        FIN(5, "FIN", "完成"),

        /**
         * OPR：数据接收中.
         */
        OPR(6, "OPR", "数据接收中"),

        /**
         * APW：银行人工审批.
         */
        APW(7, "APW", "银行人工审批");

        /**
         * code.
         */
        private final int code;

        /**
         * name.
         */
        private final String name;

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
        public static ReqSts getByCode(final Integer code) {
            return Arrays.stream(ReqSts.values()).filter(i -> i.getCode() == code).findFirst().orElse(null);
        }

        /**
         * 根据name获取.
         *
         * @param name name
         * @return 结果
         */
        public static ReqSts getByName(final String name) {
            return Arrays.stream(ReqSts.values()).filter(i -> i.getName().equals(name)).findFirst().orElse(null);
        }
    }

    /**
     * 业务处理结果.
     */
    @Getter
    @AllArgsConstructor
    public enum RtnFlg {

        /**
         * S：成功 银行支付成功.
         */
        S(1,"S", "成功"),

        /**
         * F：失败 银行支付失败.
         */
        F(2,"F", "失败"),

        /**
         * B：退票 银行支付被退票.
         */
        B(3,"B", "退票"),

        /**
         * R：否决 企业审批否决.
         */
        R(4,"R", "否决"),

        /**
         * D：过期 企业过期不审批.
         */
        D(5,"D", "过期"),

        /**
         * C：撤消 企业撤销.
         */
        C(6,"C", "撤消"),

        /**
         * U：挂账 银行挂账.
         */
        U(7,"U", "挂账");

        /**
         * code.
         */
        private final int code;

        /**
         * name.
         */
        private final String name;

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
        public static RtnFlg getByCode(final Integer code) {
            return Arrays.stream(RtnFlg.values()).filter(i -> i.getCode() == code).findFirst().orElse(null);
        }

        /**
         * 根据name获取.
         *
         * @param name name
         * @return 结果
         */
        public static RtnFlg getByName(final String name) {
            return Arrays.stream(RtnFlg.values()).filter(i -> i.getName().equals(name)).findFirst().orElse(null);
        }
    }

    /**
     * 结算通道.
     */
    @Getter
    @AllArgsConstructor
    public enum StlChn {

        /**
         * G：普通.
         */
        G("G", "普通"),

        /**
         * Q：快速.
         */
        Q("Q", "快速"),

        /**
         * R：实时-超网.
         */
        R("R", "实时-超网"),

        /**
         * I：智能路由.
         */
        I("I", "智能路由");

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
        public static StlChn getByCode(final String code) {
            return Arrays.stream(StlChn.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
        }
    }

    /**
     * 业务种类.
     */
    @Getter
    @AllArgsConstructor
    public enum TrsTyp {

        /**
         * 100001：普通汇兑 （默认值）.
         */
        T_100001("100001", "普通"),

        /**
         * 101001：慈善捐款.
         */
        T_101001("101001", "慈善捐款"),

        /**
         * 101002：其他.
         */
        T_101002("101002", "其他");

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
        public static StlChn getByCode(final String code) {
            return Arrays.stream(StlChn.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
        }
    }

    /**
     * 请求状态.
     */
    @Getter
    @AllArgsConstructor
    public enum AutStr {

        /**
         * OPR：接收中.
         */
        OPR("OPR", "接收中"),

        /**
         * NTE：待处理.
         */
        NTE("NTE", "待处理"),

        /**
         * FIN：经办受理完成.
         */
        FIN("FIN", "经办受理完成");

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
        public static AutStr getByCode(final String code) {
            return Arrays.stream(AutStr.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
        }
    }

    /**
     * 处理结果.
     */
    @Getter
    @AllArgsConstructor
    public enum RtnStr {

        /**
         * F：失败.
         */
        F("F", "失败"),

        /**
         * S：成功.
         */
        S("S", "成功");

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
        public static RtnStr getByCode(final String code) {
            return Arrays.stream(RtnStr.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
        }

    }

}
