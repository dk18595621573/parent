package com.cloud.common.enums;

/**
 *  采购类型（采购入仓，一件代发）
 */
public enum PurchaseType {
    PURCHASE_WAREHOUSING(1,"采购入仓"),
    DROP_SHIPPING(2,"一件代发")
    ;


    private final Integer code;

    private final String msg;

    private PurchaseType(Integer code, String msg) {
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
