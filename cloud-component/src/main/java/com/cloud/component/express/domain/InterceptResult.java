package com.cloud.component.express.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 拦截返回
 * @author nlsm
 */
@Data
public class InterceptResult implements Serializable {

    /** 是否拦截成功 */
    private Boolean success;

    /** 错误状态码 */
    private String errorCode;

    /** 错误信息 */
    private String message;

    public InterceptResult(String errorCode, String message) {
        this.success = false;
        this.errorCode = errorCode;
        this.message = message;
    }

    public InterceptResult(Boolean success) {
        this.success = success;
    }
}
