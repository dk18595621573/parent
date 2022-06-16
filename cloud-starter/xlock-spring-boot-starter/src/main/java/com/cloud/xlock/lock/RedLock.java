package com.cloud.xlock.lock;

import com.cloud.xlock.model.KeyInfo;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;

/**
 * 红锁操作服务.
 *
 * @author breggor
 */
public class RedLock extends BaseLock implements Lock {

    @Override
    public RLock getLock(final KeyInfo keyInfo) {
        RLock[] lockList = new RLock[keyInfo.getKeys().size()];
        for (int i = 0; i < keyInfo.getKeys().size(); i++) {
            lockList[i] = getRedissonClient().getLock(keyInfo.getKeys().get(i));
        }
        return new RedissonRedLock(lockList);
    }
}
