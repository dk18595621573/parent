package com.cloud.component.cmb.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 请求名枚举.
 *
 * @author Luo
 * @date 2023-03-07 9:13
 */
public class FunCodeEnum {

    /**
     * 账务查询.
     */
    @Getter
    @AllArgsConstructor
    public enum AccountQuery {

        /**
         * 可经办业务模式查询.
         */
        DCLISMOD,

        /**
         * 查询可经办的账户列表.
         */
        DCLISACC,

        /**
         * 账户详细信息查询.
         */
        NTQACINF,

        /**
         * 查询账户历史余额.
         */
        NTQABINF,

        /**
         * 查询分行号信息.
         */
        NTACCBBK,

        /**
         * 批量查询余额.
         */
        NTQADINF,

        /**
         * 账户交易信息查询.
         */
        DCTRSINF,

        /**
         * 单笔回单查询.
         */
        DCSIGREC,

        /**
         * 电子回单异步查询.
         */
        ASYCALHD,

        /**
         * 异步打印结果查询.
         */
        DCTASKID;

    }

    /**
     * 支付转账.
     */
    @Getter
    @AllArgsConstructor
    public enum Pay {

        /**
         * 企银支付单笔经办.
         */
        BB1PAYOP,

        /**
         * 企银支付业务查询.
         */
        BB1PAYQR,

        /**
         * 企银支付批量经办.
         */
        BB1PAYBH,

        /**
         * 企银批量支付批次查询.
         */
        BB1QRYBT,

        /**
         * 企银批量支付明细查询.
         */
        BB1QRYBD,

        /**
         * 支付退票明细查询.
         */
        BB1PAYQB;

    }

}
