package com.cloud.xlock.config;

/**
 * 锁类型.
 *
 * @author breggor
 */
public enum XLockType {

    /**
     * 可重入锁.
     */
    REENTRANT,

    /**
     * 公平锁.
     */
    FAIR,

    /**
     * 联锁.
     */
    MULTI,

    /**
     * 红锁.
     */
    RED,

    /**
     * 读锁.
     */
    READ,

    /**
     * 写锁.
     */
    WRITE

}
