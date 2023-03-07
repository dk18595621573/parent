package com.cloud.component.cmb.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 招商银行 接口 异常.
 *
 * @author Luo
 * @date 2023-3-6 15:32:06
 **/
@Getter
@Setter
public class CmbApiException extends Exception {

    private static final long serialVersionUID = -7018782623990497946L;

    /**
     * 错误码.
     */
    private String errorCode;

    /**
     * 错误描述.
     */
    private String errorMsg;

    public CmbApiException(final String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public CmbApiException(final String errorCode, final String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public CmbApiException(final Throwable cause) {
        super(cause);
    }

}
