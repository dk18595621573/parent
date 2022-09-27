package com.cloud.common.enums;

/**
 * 税票要求
 */
public enum TaxRequire {
    TAX_INCLUDED_TYPE(1, "含税（型号对应）"),
    TAX_INCLUDED(2, "含税"),
    TAX_NO(3, "无需税票");

    private final Integer code;
    private final String msg;

    TaxRequire(int code, String msg) {
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
