package com.cloud.framework.lock.lock;

import com.cloud.framework.lock.model.KeyInfo;
import org.redisson.api.RLock;

/**
 * 公平锁操作服务.
 *
 * @author breggor
 */
public class FairLock extends BaseLock implements Lock {

    @Override
    public RLock getLock(final KeyInfo keyInfo) {
        return getRedissonClient().getFairLock(keyInfo.getKey());
    }
}
