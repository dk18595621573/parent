package com.cloud.common.enums;

import java.util.Objects;

/**
 * 群接龙模式
 */
public enum OrderMarketingModel {
    INQUIRY_MODEL(1, "询报价"),
    MARKETING_MODEL(2, "一口价"),
    TRIVALENT_MODEL(3, "抢单模式");


    private final Integer code;

    private final String msg;

    OrderMarketingModel(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static OrderMarketingModel fromCode(Integer code) {
        if (Objects.nonNull(code)) {
            for (OrderMarketingModel status : OrderMarketingModel.values()) {
                if (status.getCode().equals(code)) {
                    return status;
                }
            }
        }
        return null;
    }
}
