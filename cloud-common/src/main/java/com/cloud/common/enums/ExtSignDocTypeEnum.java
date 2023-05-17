package com.cloud.common.enums;

import java.util.Objects;

/**
 * 合同类型
 *
 * @author author
 */
public enum ExtSignDocTypeEnum {
    FRAME(0, "框架合同"),
    SALE(1, "销售合同"),
    RECEIPT(2, "收货确认单");

    private final int code;
    private final String info;

    ExtSignDocTypeEnum(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public static ExtSignDocTypeEnum queryEnum(Integer code) {
        ExtSignDocTypeEnum[] values = ExtSignDocTypeEnum.values();
        for (ExtSignDocTypeEnum value : values) {
            if (Objects.equals(code, value.getCode())) {
                return value;
            }
        }
        return null;
    }
}
