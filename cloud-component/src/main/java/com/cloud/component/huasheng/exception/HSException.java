package com.cloud.component.huasheng.exception;

/**
 * 华盛异常
 */
public class HSException extends RuntimeException {

    /**
     * 应答状态
     */
    private String respCode;

    public HSException(final String message) {
        super(message);
    }

    public HSException(final String respCode, final String message) {
        super(message);
        this.respCode = respCode;
    }

    public String getRespCode() {
        return respCode;
    }
}
