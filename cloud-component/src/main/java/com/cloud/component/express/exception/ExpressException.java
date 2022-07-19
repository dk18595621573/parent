package com.cloud.component.express.exception;

import com.cloud.component.express.consts.ErrorCode;

/**
 * 快递接口异常.
 *
 * @author zenghao
 * @date 2022/7/19
 */
public class ExpressException extends RuntimeException {

    private final ErrorCode code;

    public ExpressException(final ErrorCode code) {
        super(code.getMsg());
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }
}
