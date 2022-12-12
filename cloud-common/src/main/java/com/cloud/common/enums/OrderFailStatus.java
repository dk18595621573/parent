package com.cloud.common.enums;

import java.util.Objects;

/**
 * 失败订单状态 1：需求方撤回；2：退货追单；3：供应商取消；4：超时发货
 */
public enum OrderFailStatus implements BaseEnum {
    DEMAND_REVOKE(1, "需求方撤回"),
    AFTER_SALE(2, "退货追单"),
    AFTER_CANCEL(3, "供应商取消"),
    OVERTIME_DELIVERY(4, "超时发货"),
    ;


    private final Integer code;

    private final String msg;

    OrderFailStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static OrderFailStatus fromCode(Integer code) {
        if (Objects.nonNull(code)) {
            for (OrderFailStatus status : OrderFailStatus.values()) {
                if (status.getCode().equals(code)) {
                    return status;
                }
            }
        }
        return null;
    }
}
