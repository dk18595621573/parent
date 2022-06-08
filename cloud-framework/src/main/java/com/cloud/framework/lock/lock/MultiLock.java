package com.cloud.framework.lock.lock;

import com.cloud.framework.lock.model.KeyInfo;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;

/**
 * 联锁操作服务.
 *
 * @author breggor
 */
public class MultiLock extends BaseLock implements Lock {

    @Override
    public RLock getLock(final KeyInfo keyInfo) {
        RLock[] lockList = new RLock[keyInfo.getKeys().size()];
        for (int i = 0; i < keyInfo.getKeys().size(); i++) {
            lockList[i] = getRedissonClient().getLock(keyInfo.getKey());
        }
        return new RedissonMultiLock(lockList);
    }
}
