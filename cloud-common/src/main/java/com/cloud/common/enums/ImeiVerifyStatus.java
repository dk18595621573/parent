package com.cloud.common.enums;

/**
 * @author dk185
 * 串码验证
 */
public enum ImeiVerifyStatus {
    STAY_QUERY(0, "默认(待查询)"),
    STAY_AUDIT(1, "待审核"),
    STAY_AFFIRM(2, "待确认"),
    NORMAL(3, "正常"),
    ABNORMAL(4, "异常");

    private final Integer code;
    private final String msg;


    ImeiVerifyStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
