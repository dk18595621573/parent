package com.cloud.component.shadow.exception;

/**
 * 影刀RPA 运行时  异常.
 *
 * @author Luo
 * @date 2023-3-17 13:22:04
 */
public class ShadowBotRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -397441706693761861L;

    public ShadowBotRuntimeException() {
        super();
    }

    public ShadowBotRuntimeException(final String message) {
        super(message);
    }

    public ShadowBotRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ShadowBotRuntimeException(final Throwable cause) {
        super(cause);
    }

    protected ShadowBotRuntimeException(final String message, final Throwable cause,
                                        final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
