package com.cloud.idempotent.exception;

/**
 * 幂等性校验异常.
 *
 * @author zenghao
 * @date 2023/2/27
 */
public class IdempotentException extends RuntimeException {

    public IdempotentException(final String message) {
        super(message);
    }

    public IdempotentException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
