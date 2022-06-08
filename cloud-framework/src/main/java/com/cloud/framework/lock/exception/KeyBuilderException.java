package com.cloud.framework.lock.exception;

/**
 * key构建异常.
 *
 * @author breggor
 */
public class KeyBuilderException extends RuntimeException {

    private static final long serialVersionUID = 713051615398843448L;

    public KeyBuilderException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public KeyBuilderException(final String message) {
        super(message);
    }
}
