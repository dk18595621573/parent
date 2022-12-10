package com.cloud.common.enums;

/**
 * @author mft
 * <p>
 * 法大大
 */

public enum ContractStatus implements BaseEnum {

    /**
     * 采购合同
     */
    PURCHASE(1, "采购框架协议");


    private final Integer code;

    private final String msg;

    ContractStatus(Integer code, String msg) {
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
