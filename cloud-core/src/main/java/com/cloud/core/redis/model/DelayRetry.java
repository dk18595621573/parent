package com.cloud.core.redis.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 延迟重试数据.
 *
 * @author zenghao
 * @date 2022/12/12
 */
@Data
public class DelayRetry<T> implements Serializable {

    /**
     * 默认最大重试次数 5次
     */
    public static final int MAX_RETRY_COUNT = 5;

    /**
     * 默认每次重试时间间隔 15秒
     */
    public static final long DEFAULT_INTERVAL = 15L;

    /**
     * 当前重试次数
     */
    private int count;

    /**
     * 最大重试次数
     */
    private int maxCount;

    /**
     * 是否使用相同的时间间隔 否的话会使用重试次数*间隔时间 默认false
     */
    private boolean useSameInterval;

    /**
     * 每次重试时间间隔 单位:秒
     */
    private long interval = 1L;

    /**
     * 数据
     */
    private T data;

    public DelayRetry() {
    }

    public DelayRetry(final T data) {
        this(0, DEFAULT_INTERVAL, data);
    }

    public DelayRetry(final int count, final long interval, final T data) {
        this(count, interval, false, data);
    }

    public DelayRetry(final int count, final long interval, final boolean useSameInterval, final T data) {
        this(count, MAX_RETRY_COUNT, interval, useSameInterval, data);
    }

    public DelayRetry(final int count, final int maxCount, final long interval, final boolean useSameInterval, final T data) {
        this.count = count;
        this.maxCount = maxCount;
        this.interval = interval;
        this.useSameInterval = useSameInterval;
        this.data = data;
    }

    /**
     * 下次重试时间间隔
     *
     * @return 次数 * 间隔
     */
    public long nextTime() {
        return useSameInterval ? interval : count * interval;
    }

    /**
     * 自增重试次数
     *
     * @return
     */
    public int addCount() {
        return count++;
    }

    /**
     * 是否继续重试 会自增重试次数
     *
     * @return
     */
    public boolean hasNext() {
        return addCount() < maxCount;
    }

}
