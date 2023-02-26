package com.cloud.component.express.consts;

/**
 * 物流付款方式
 * @author nlsm
 */
public enum LogisticsPayType {
    /** 寄付 */
    PAID_BY_SHIPPER("1", "寄付"),
    /** 到付 */
    FREIGHT_COLLECT_PAYMENT("2", "到付"),
    /** 转第三方月结 */
    THIRD_PARTY_MONTHLY_STATEMENT("3", "转第三方月结"),
    /** 寄付月结 */
    MONTHLY_STATEMENT("4", "寄付月结"),
    ;

    private final String code;
    private final String msg;

    LogisticsPayType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
