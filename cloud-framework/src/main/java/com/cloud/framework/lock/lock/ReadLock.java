package com.cloud.framework.lock.lock;

import com.cloud.framework.lock.model.KeyInfo;
import org.redisson.api.RLock;

/**
 * 读锁操作服务.
 *
 * @author breggor
 */
public class ReadLock extends BaseLock implements Lock {

    @Override
    public RLock getLock(final KeyInfo keyInfo) {
        return getRedissonClient().getReadWriteLock(keyInfo.getKey()).readLock();
    }
}
