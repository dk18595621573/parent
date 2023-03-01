package com.cloud.common.enums;

/**
 * 群接龙模式
 */
public enum OrderMarketingModel {
    INQUIRY_MODEL(1, "询报价"),
    MARKETING_MODEL(2, "一口价");


    private final Integer code;

    private final String msg;

    private OrderMarketingModel(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
