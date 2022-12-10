package com.cloud.common.enums;

/**
 * @author mft
 * <p>
 * 银联打款
 */

public enum ChinaPayType implements BaseEnum {

    /**
     * 打款银行账号
     */
    PAY_BANK_ACCOUNT(0, "215500700"),

    /**
     * 打款开户银行
     */
    PAY_ACCOUNT_BANK(0, "上海银联电子支付服务有限公司"),

    /**
     * 银联打款状态
     */
    ORDER_STATUS(0, "0000"),

    /**
     * 银联企业代码类型 1：统一信用代码 3：工商注册号
     */
    KEY_TYPE_CREDIT(1, "1"),

    /**
     * 银联企业代码类型 1：统一信用代码 3：工商注册号
     */
    KEY_TYPE(3, "3");

    private final Integer code;

    private final String msg;

    ChinaPayType(Integer code, String msg) {
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
