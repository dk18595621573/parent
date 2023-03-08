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
        AUT("AUT", "帐务查询"),

        /**
         * NTE：终审完毕.
         */
        NTE("NTE", "终审完毕"),

        /**
         * BNK：银行处理中.
         */
        BNK("BNK", "银行处理中"),

        /**
         * WRF：银行处理中.
         */
        WRF("WRF", "银行处理中"),

        /**
         * FIN：完成.
         */
        FIN("FIN", "完成"),

        /**
         * OPR：数据接收中.
         */
        OPR("OPR", "数据接收中");

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
        public static ReqSts getByCode(final String code) {
            return Arrays.stream(ReqSts.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
        }
    }

    /**
     * 业务处理结果.
     */
    @Getter
    @AllArgsConstructor
    public enum RtnFlg {

        /**
         * S：成功银行支付成功.
         */
        S("S", "成功银行支付成功"),

        /**
         * F：失败银行支付失败.
         */
        F("F", "失败银行支付失败"),

        /**
         * B：退票银行支付被退票.
         */
        B("B", "退票银行支付被退票"),

        /**
         * R：否决企业审批否决.
         */
        R("R", "否决企业审批否决"),

        /**
         * D：过期企业过期不审批.
         */
        D("D", "过期企业过期不审批"),

        /**
         * C：撤消企业撤销.
         */
        C("C", "撤消企业撤销"),

        /**
         * U：银行挂账.
         */
        U("U", "银行挂账");

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
        public static RtnFlg getByCode(final String code) {
            return Arrays.stream(RtnFlg.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
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
