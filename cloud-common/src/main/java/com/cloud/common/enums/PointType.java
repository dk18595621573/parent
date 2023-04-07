package com.cloud.common.enums;

public enum PointType implements BaseEnum{

    BATCH_COMPETE(1,"一键抢单"),
    BATCH_COMPETE_CLOSE(2,"一键抢单关闭"),
    BATCH_COMPETE_RECOMMEND(3,"推荐页抢单"),
    BATCH_COMPETE_DOUBLE_CONFIRM(4,"一键抢单2次确认"),
    BATCH_COMPETE_CLOSE_DOUBLE_CLOSE(5,"一键抢单二次关闭"),
    HUAWEI_BATCH_COMPETE_RECOMMEND(6,"华为推荐页抢单");

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
