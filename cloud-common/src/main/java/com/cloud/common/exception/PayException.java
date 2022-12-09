package com.cloud.common.exception;

/**
 * 业务异常
 *
 * @author author
 */
public final class PayException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PayException(Throwable cause) {
        super(cause);
    }
}