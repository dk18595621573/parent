package com.cloud.common.enums;

/**
 *  发布方式 1： 立即发布 2：稍后发布； 3：定时发布
 */
public enum ReleaseType {

    PUBLISH_NOW(1L,"立即发布"),
    PUBLISH_LATER(2L,"稍后发布"),
    PUBLISH_REGULAR(3L,"定时发布");

    private final Long code;

    private final String msg;

    private ReleaseType(Long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Long getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
