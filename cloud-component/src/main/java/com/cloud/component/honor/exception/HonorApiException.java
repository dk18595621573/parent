package com.cloud.component.honor.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 荣耀 接口 异常.
 *
 * @author Luo
 * @date 2023-3-17 13:22:16
 **/
@Getter
@Setter
public class HonorApiException extends Exception {

    private static final long serialVersionUID = -7018782623990497946L;

    /**
     * 错误码.
     */
    private String errorCode;

    /**
     * 错误描述.
     */
    private String errorMsg;

    public HonorApiException(final String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public HonorApiException(final String errorCode, final String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public HonorApiException(final Throwable cause) {
        super(cause);
    }

}
