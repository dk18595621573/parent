package com.cloud.xlock.config;

/**
 * 公共常量.
 *
 * @author breggor
 */
public interface XLockConsts {

    /**
     * 锁的key的分隔符.
     */
    String KEY_SPLIT_MARK = ":";

    /**
     * 锁的前缀.
     */
    String KEY_PREFIX = "xlock" + KEY_SPLIT_MARK + "key" + KEY_SPLIT_MARK;

}
