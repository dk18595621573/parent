package com.cloud.common.enums;

/**
 * @author mft
 * <p>
 * 法大大
 */

public enum FadadaStatus {

    /**
     * 签署回调code
     */
    EXTSIGN_CALLBACK_CODE(0, "3000"),

    /**
     * 实名认证回调
     */
    VERIFY_CALLBACK_FOUR(4, "4"),

    /**
     * 合同归档code
     */
    CONTRACT_FILING_CODE(1000, "1000"),

    /**
     * 模板文本域
     */
    TEMPLATE_FIELD_COMPANY(1, "company");


    private final Integer code;

    private final String msg;

    FadadaStatus(Integer code, String msg) {
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
