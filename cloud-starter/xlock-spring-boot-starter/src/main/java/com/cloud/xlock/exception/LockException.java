package com.cloud.xlock.exception;

/**
 * 锁异常.
 *
 * @author breggor
 */
public class LockException extends RuntimeException {

    public LockException(final String ex) {
        super(ex);
    }
}
