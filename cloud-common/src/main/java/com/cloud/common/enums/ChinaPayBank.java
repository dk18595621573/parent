package com.cloud.common.enums;

/**
 * @author mft
 * <p>
 * 银联打款
 */

public enum ChinaPayBank implements BaseEnum {

    /**
     * 中国银行
     */
    B_O_C(5, "中国银行"),

    /**
     * 中国建设银行
     */
    C_C_B(6, "中国建设银行"),

    /**
     * 中国工商银行
     */
    I_C_B_C(7, "中国工商银行"),

    /**
     * 中国交通银行
     */
    B_C_M(8, "中国交通银行"),

    /**
     * 中国农业银行
     */
    A_B_C(9, "中国农业银行"),

    /**
     * 中国邮政储蓄银行
     */
    P_S_B_C(10, "中国邮政储蓄银行"),

    /**
     * 招商银行
     */
    C_M_B(11, "招商银行"),

    /**
     * 兴业银行
     */
    C_I_B(12, "兴业银行"),

    /**
     * 浦发银行
     */
    S_P_D_B(13, "浦发银行"),

    /**
     * 平安银行
     */
    P_A_B(14, "平安银行");

    private final Integer code;

    private final String msg;

    ChinaPayBank(Integer code, String msg) {
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
