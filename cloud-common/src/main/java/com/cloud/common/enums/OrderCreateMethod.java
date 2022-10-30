package com.cloud.common.enums;

import java.util.Objects;

/**
 * 订单创建方式（1. 界面新建；2. Excel导入；3.erp拉取）
 */
public enum OrderCreateMethod {
    NEW_INTERFACE(1, "界面新建"),
    EXCEL_IMPORT(2, "Excel导入"),
    ERP_PULL(3, "erp拉取"),
    SOLITAIRE_MARKETING(4, "接龙抢单")
    ;


    private final Integer code;

    private final String msg;

    OrderCreateMethod(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static OrderCreateMethod fromCode(Integer code) {
        if (Objects.nonNull(code)) {
            for (OrderCreateMethod status : OrderCreateMethod.values()) {
                if (status.getCode().equals(code)) {
                    return status;
                }
            }
        }
        return null;
    }
}
