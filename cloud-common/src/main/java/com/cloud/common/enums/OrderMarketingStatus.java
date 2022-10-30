package com.cloud.common.enums;

import java.util.Objects;

/**
 * 接龙订单状态 1: 新建采购；2: 报价中；3:已完结
 */
public enum OrderMarketingStatus {
    NOT_QUOTED(1, "新建采购"),
    IN_QUOTATION(2, "报价中"),
    COMPLETED(3, "已完结"),
    ;


    private final Integer code;

    private final String msg;

    OrderMarketingStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static OrderMarketingStatus fromCode(Integer code) {
        if (Objects.nonNull(code)) {
            for (OrderMarketingStatus status : OrderMarketingStatus.values()) {
                if (status.getCode().equals(code)) {
                    return status;
                }
            }
        }
        return null;
    }
}
