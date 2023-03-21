package com.cloud.component.ecss.exception;

/**
 * 广移 运行时  异常.
 *
 * @author Luo
 * @date 2023-3-17 13:22:04
 */
public class ECSSRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -397441706693761861L;

    public ECSSRuntimeException() {
        super();
    }

    public ECSSRuntimeException(final String message) {
        super(message);
    }

    public ECSSRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ECSSRuntimeException(final Throwable cause) {
        super(cause);
    }

    protected ECSSRuntimeException(final String message, final Throwable cause,
                                   final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
