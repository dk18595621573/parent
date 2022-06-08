package com.cloud.framework.lock.lock;

import com.cloud.framework.lock.exception.LockException;
import com.cloud.framework.lock.model.KeyInfo;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Objects;

/**
 * 锁服务有状态数据多线程处理.
 *
 * @author breggor
 */
public abstract class BaseLock implements Lock {

    /**
     * redisson 客户端.
     */
    private RedissonClient redissonClient;

    public RedissonClient getRedissonClient() {
        return redissonClient;
    }

    @Autowired
    public void setRedissonClient(@Qualifier("xLockRedissonClient") final RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public boolean isLocked(final KeyInfo keyInfo) {
        Objects.requireNonNull(keyInfo, "keyInfo: 不能为null");
        return getLock(keyInfo).isLocked();
    }

    @Override
    public boolean tryLock(final KeyInfo keyInfo) {
        RLock lock = getLock(keyInfo);
        try {
            if (enableLeaseTime(keyInfo)) {
                if (enableWaitTime(keyInfo)) {
                    return lock.tryLock(keyInfo.getWaitTime(), keyInfo.getLeaseTime(), keyInfo.getTimeUnit());
                }
                return lock.tryLock(keyInfo.getLeaseTime(), keyInfo.getTimeUnit());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("thread interrupted");
        }
        return false;
    }

    @Override
    public void lock(final KeyInfo keyInfo) {
        Objects.requireNonNull(keyInfo, "keyInfo: 不能为null");

        if (keyInfo.isEmpty()) {
            throw new LockException("keyInfo keys不能为空");
        }

        RLock lock = getLock(keyInfo);

        if (!enableLeaseTime(keyInfo) && !enableWaitTime(keyInfo)) {
            lock.lock();
            return;
        }

        if (enableLeaseTime(keyInfo) && !enableWaitTime(keyInfo)) {
            lock.lock(keyInfo.getLeaseTime(), keyInfo.getTimeUnit());
            return;
        }

        if (enableLeaseTime(keyInfo) && enableWaitTime(keyInfo)) {
            try {
                if (!lock.tryLock(keyInfo.getWaitTime(), keyInfo.getLeaseTime(), keyInfo.getTimeUnit())) {
                    throw new LockException("lock fail");
                }
            } catch (InterruptedException interruptedException) {
                throw new RuntimeException("thread interrupted");
            }
            return;
        }

        lock.lock();
    }

    @Override
    public void unlock(final KeyInfo keyInfo) {
        RLock lock = getLock(keyInfo);
        if (lock != null) {
            lock.unlock();
        }
    }

    /**
     * 实现不同类型lock.
     *
     * @param keyInfo 锁的key信息
     * @return 锁对象
     */
    protected abstract RLock getLock(KeyInfo keyInfo);
}
