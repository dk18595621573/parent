package com.cloud.common.enums;

import java.util.Objects;

/**
 * 供应商询价采购出价状态 竞价中、已成交、未达成、已撤销
 */
public enum InquiryBiddingStatus implements BaseEnum {
    BIDDING(1, "竞价中"),
    COMPLETE(2, "已成交"),
    NOT_COMPLETE(3, "未达成"),
    REVOKE_INQUIRY(4,"已撤销"),
    REVOKE_DELIVERED(5,"已毁单")
    ;

    private final Integer code;

    private final String msg;

    InquiryBiddingStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static InquiryBiddingStatus fromCode(Integer code) {
        if (Objects.nonNull(code)) {
            for (InquiryBiddingStatus status : InquiryBiddingStatus.values()) {
                if (status.getCode().equals(code)) {
                    return status;
                }
            }
        }
        return null;
    }
}
