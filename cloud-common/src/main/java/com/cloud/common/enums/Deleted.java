package com.cloud.common.enums;

/**
 * 是否被删除 0 未删除 1 已删除
 */
public enum Deleted implements BaseEnum {

    NOT_DELETE(0, "未删除"),
    ALREADY_DELETE(1, "已删除"),
    ;


    private final Integer code;

    private final String msg;

    Deleted(Integer code, String msg) {
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
