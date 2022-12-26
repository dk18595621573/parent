package com.cloud.webmvc.exception;

/**
 * 认证异常.
 *
 * @author zenghao
 * @date 2022/12/20
 */
public class AuthorizationException extends RuntimeException {

    public AuthorizationException(final String message) {
        super(message);
    }
}
