package com.cloud.common.enums;

/**
 * 合同类型
 *
 * @author author
 */
public enum ExtSignDocTypeEnum {
    FRAME(0, "框架合同"), SALE(1, "销售合同");

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
}
