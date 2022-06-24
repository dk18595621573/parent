package com.cloud.common.enums;

/**
 * @author mft
 * 
 *         银联打款
 */

public enum ChinaPayStatus {

    /** 中国银行 */
    B_O_C(5, "中国银行"),

    /** 中国建设银行 */
    C_C_B(6, "中国建设银行"),

    /** 中国工商银行 */
    I_C_B_C(7, "中国工商银行"),

    /** 中国交通银行 */
    B_C_M(8, "中国交通银行"),

    /** 中国农业银行 */
    A_B_C(9, "中国农业银行"),

    /** 打款银行账号 */
    PAY_BANK_ACCOUNT(0, "215500700"),

    /** 打款开户银行 */
    PAY_ACCOUNT_BANK(0, "上海银联电子支付服务有限公司"),

    /** 银联打款状态 */
    ORDER_STATUS(0, "0000"),

    /** 银联企业代码类型 1：统一信用代码 3：工商注册号 */
    KEY_TYPE_CREDIT(1, "1"),

    /** 银联企业代码类型 1：统一信用代码 3：工商注册号 */
    KEY_TYPE(3, "3");

    private final Integer code;

    private final String msg;

    ChinaPayStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
