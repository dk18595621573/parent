package com.cloud.framework.lock.lock;

import com.cloud.framework.lock.model.KeyInfo;
import org.redisson.api.RLock;

/**
 * 可重入锁加锁服务.
 *
 * @author breggor
 */
public class ReentrantLock extends BaseLock implements Lock {

    @Override
    public RLock getLock(final KeyInfo keyInfo) {
        return getRedissonClient().getLock(keyInfo.getKey());
    }
}
