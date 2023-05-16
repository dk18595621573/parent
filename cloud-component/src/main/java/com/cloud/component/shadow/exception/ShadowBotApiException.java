package com.cloud.component.shadow.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 影刀RPA 接口 异常.
 *
 * @author Luo
 * @date 2023-3-17 13:22:16
 **/
@Getter
@Setter
public class ShadowBotApiException extends Exception {

    private static final long serialVersionUID = -7018782623990497946L;

    /**
     * 错误码.
     */
    private Integer errorCode;

    /**
     * 错误描述.
     */
    private String errorMsg;

    public ShadowBotApiException(final String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public ShadowBotApiException(final Integer errorCode, final String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ShadowBotApiException(final Throwable cause) {
        super(cause);
    }

}
