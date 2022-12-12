package com.cloud.common.enums;

import java.util.Objects;

/**
 * @author wyt
 * @date 2022/11/14 13:47
 */
public enum OrderMarketingSubStatus implements BaseEnum {

    WAIT_SNATCH(0, "待抢单"),
    ORDER_SNATCHED(1, "已抢单"),
    ;
    private final Integer code;

    private final String msg;

    OrderMarketingSubStatus(Integer code, String msg) {
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
