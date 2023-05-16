package com.cloud.common.enums;

public enum AllOrderFieldType implements BaseEnum {

    DEFAULT(0, "默认"),
    USER(1, "用户"),
    ;


    private final Integer code;

    private final String msg;

    AllOrderFieldType(Integer code, String msg) {
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
