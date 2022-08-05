package com.cloud.common.utils.uuid;

import cn.hutool.core.util.StrUtil;

import java.io.Serializable;

/**
 * 短id生成.
 *  以秒为单位生成
 *
 * @author zenghao
 * @date 2022/8/5
 */
public class ShortSnowflake implements Serializable {

    /**
     * 初始时间 2022-01-01 00:00:00
     */
    private final long start = 1640966400L;

    /**
     * 机器数占3位，即最多8台
     */
    private final int workerIdBits = 3;
    /**
     * 序列数占4位，即每秒最多生成16个号
     */
    private final long sequenceBits = 4;

    /**
     * 最大机器标识（初始化时不允许超过此值）
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    /**
     * 最大序列号（超过此序列号需要等待到下一秒）
     */
    private final long maxSequence = -1L ^ (-1L << sequenceBits);

    /**
     * 机器节点左移位
     */
    private final long workerIdShift = sequenceBits;
    /**
     * 时间戳左移位
     */
    private final long timestampLeftShift = sequenceBits + workerIdShift;

    /**
     * 记录上次序列号生成的时间戳
     */
    private long lastTimestamp = -1L;
    /**
     * 记录上次生成的序列号
     */
    private long sequence = 0L;

    /**
     * 机器id标识
     */
    private final long workerId;

    public ShortSnowflake(long workerId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(StrUtil.format("worker Id can't be greater than {} or less than 0", maxWorkerId));
        }
        this.workerId = workerId;
    }

    public long getGenerateDateTime(long id) {
        return (id >> timestampLeftShift & ~(-1L << 30L)) + start;
    }

    public synchronized long nextId() {
        long timestamp = genTime();
        if (timestamp < lastTimestamp) {
            if(lastTimestamp - timestamp < 2000){
                // 容忍2秒内的回拨，避免NTP校时造成的异常
                timestamp = lastTimestamp;
            } else{
                // 如果服务器时间有问题(时钟后退) 报错。
                throw new IllegalStateException(StrUtil.format("Clock moved backwards. Refusing to generate id for {}ms", lastTimestamp - timestamp));
            }
        }

        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & maxSequence;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - start) << timestampLeftShift) | (workerId << workerIdShift) | sequence;
    }

    public String nextIdStr() {
        return Long.toString(nextId());
    }

    /**
     * 循环等待下一个时间
     *
     * @param lastTimestamp 上次记录的时间
     * @return 下一个时间
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = genTime();
        // 循环直到操作系统时间戳变化
        while (timestamp == lastTimestamp) {
            timestamp = genTime();
        }
        if (timestamp < lastTimestamp) {
            // 如果发现新的时间戳比上次记录的时间戳数值小，说明操作系统时间发生了倒退，报错
            throw new IllegalStateException(
                StrUtil.format("Clock moved backwards. Refusing to generate id for {}ms", lastTimestamp - timestamp));
        }
        return timestamp;
    }

    /**
     * 生成秒为单位的时间戳
     *
     * @return 时间戳
     */
    private long genTime() {
        return System.currentTimeMillis() / 1000;
    }

//    public static void main(String[] args) {
//        for (int i = 0; i < 100; i++) {
//            System.out.println(Constants.SHORT_SNOWFLAKE.nextIdStr());;
//        }
//    }
}
