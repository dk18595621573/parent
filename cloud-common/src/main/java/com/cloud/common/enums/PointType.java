package com.cloud.common.enums;

public enum PointType implements BaseEnum{

    BATCH_COMPETE(1,"一键抢单"),
    BATCH_COMPETE_CLOSE(2,"一键抢单关闭"),
    BATCH_COMPETE_RECOMMEND(3,"推荐页抢单");

    private final Integer code;

    private final String msg;

    PointType(Integer code, String msg) {
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
