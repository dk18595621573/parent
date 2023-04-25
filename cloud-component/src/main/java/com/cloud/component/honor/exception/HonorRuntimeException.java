package com.cloud.component.honor.exception;

/**
 * 荣耀 运行时  异常.
 *
 * @author Luo
 * @date 2023-3-17 13:22:04
 */
public class HonorRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -397441706693761861L;

    public HonorRuntimeException() {
        super();
    }

    public HonorRuntimeException(final String message) {
        super(message);
    }

    public HonorRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public HonorRuntimeException(final Throwable cause) {
        super(cause);
    }

    protected HonorRuntimeException(final String message, final Throwable cause,
                                    final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
