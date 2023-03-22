package com.cloud.webmvc.exception;

/**
 * 鉴权出现异常.
 *
 * @author zenghao
 * @date 2023/3/22
 */
public class AuthenticationException extends RuntimeException {

    public AuthenticationException(final String message) {
        super(message);
    }
}
