package com.cloud.xlock.exception;

/**
 * 验证错误异常.
 *
 * @author breggor
 */
public class LockValidateException extends RuntimeException {

    private static final long serialVersionUID = -6925556550996662677L;

    public LockValidateException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public LockValidateException(final Throwable cause) {
        super(cause);
    }

    public LockValidateException(final String message) {
        super(message);
    }

    public LockValidateException() {
        super();
    }
}
