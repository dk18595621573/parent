package com.cloud.framework.lock.lock;

import com.cloud.framework.lock.config.XLockType;
import com.cloud.framework.lock.exception.LockServiceNotFoundException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.EnumMap;

/**
 * 服务Bean工厂.
 *
 * @author breggor
 */
public class LockFactory implements ApplicationContextAware {

    private static EnumMap<XLockType, Class<?>> serviceMap = new EnumMap<>(XLockType.class);

    static {
        serviceMap.put(XLockType.REENTRANT, ReentrantLock.class);
        serviceMap.put(XLockType.FAIR, FairLock.class);
        serviceMap.put(XLockType.MULTI, MultiLock.class);
        serviceMap.put(XLockType.READ, ReadLock.class);
        serviceMap.put(XLockType.RED, RedLock.class);
        serviceMap.put(XLockType.WRITE, WriteLock.class);
    }

    private ApplicationContext applicationContext;

    /**
     * 根据锁类型获取相应的服务处理类.
     *
     * @param lockType 所类型
     * @return 锁服务处理类
     * @throws LockServiceNotFoundException 无法找到锁服务异常
     */
    public Lock getService(final XLockType lockType) throws LockServiceNotFoundException {
        Lock lock = (Lock) applicationContext.getBean(serviceMap.get(lockType));
        if (lock == null) {
            throw new LockServiceNotFoundException();
        }
        return lock;
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
