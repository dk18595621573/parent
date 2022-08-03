package com.cloud.tencent.exception;

/**
 * 腾讯接口异常.
 *
 * @author zenghao
 * @date 2022/8/2
 */
public class TencentException extends RuntimeException {

    private final String errorCode;

    public TencentException(final String message, final String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public TencentException(final String message, final String errorCode, final Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
