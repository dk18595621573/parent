package com.cloud.common.enums;

/**
 * 供应商企业类型(飞单商,落地商).
 */
public enum SupplierCompanyAreaType {

    /**
     * 飞单商.
     */
    AREA_NONE_LIMIT(1, "飞单商"),
    /**
     * 落地商.
     */
    AREA_LIMIT(2, "落地商");

    private final Integer code;

    private final String msg;

    SupplierCompanyAreaType(Integer code, String msg) {
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
