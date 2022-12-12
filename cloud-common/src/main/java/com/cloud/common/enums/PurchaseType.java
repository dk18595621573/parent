package com.cloud.common.enums;

/**
 * 采购类型（批量采购，一件代发）
 */
public enum PurchaseType implements BaseEnum {
    PURCHASE_WAREHOUSING(1, "批量采购"),
    DROP_SHIPPING(2, "一件代发"),
    SOLITAIRE_MARKETING(3, "接龙抢单");


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

    public static PurchaseType fromCode(Integer code) {
        for (PurchaseType type : PurchaseType.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return PURCHASE_WAREHOUSING;
    }

    public static String convertMag(Integer code){
        for (PurchaseType type : PurchaseType.values()) {
            if (type.getCode().equals(code)) {
                return type.getMsg();
            }
        }
        return PURCHASE_WAREHOUSING.getMsg();
    }
}
