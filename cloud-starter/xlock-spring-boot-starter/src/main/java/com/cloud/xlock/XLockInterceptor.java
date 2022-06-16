package com.cloud.xlock;

import com.cloud.xlock.annotation.XLock;
import com.cloud.xlock.lock.Lock;
import com.cloud.xlock.lock.LockFactory;
import com.cloud.xlock.model.KeyInfo;
import com.cloud.xlock.model.LockedInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * 锁拦截器.
 *
 * @author breggor
 */
@Slf4j
@Aspect
public class XLockInterceptor {

    /**
     * 已锁资源.
     */
    private static final ThreadLocal<Map<String, LockedInfo>> LOCKED_HOLDER = new InheritableThreadLocal<Map<String, LockedInfo>>(
    ) {
        @Override
        protected Map<String, LockedInfo> initialValue() {
            return new HashMap<>();
        }
    };

    @Autowired
    private LockFactory lockFactory;

    @Autowired
    private XLockSpelResolver xLockSpelResolver;

    /**
     * 方法加锁.
     *
     * @param joinPoint 切面方法
     * @param xLock     锁注解
     * @return 方法返回值
     */
    @Around(value = "@annotation(xLock)")
    public Object around(final ProceedingJoinPoint joinPoint, final XLock xLock) {

        if (log.isDebugEnabled()) {
            log.debug("[分布式锁] - 拦截进入处理");
        }

        KeyInfo keyInfo = xLockSpelResolver.getKeyInfo(joinPoint, xLock);
        if (log.isDebugEnabled()) {
            log.debug("[分布式锁] - 锁key生成: keyInfo={}", keyInfo);
        }
        LOCKED_HOLDER.get().put(keyInfo.getLockId(), new LockedInfo(keyInfo));

        Lock lockService = lockFactory.getService(xLock.lockType());
        LOCKED_HOLDER.get().get(keyInfo.getLockId()).setLockService(lockService);

        lockService.lock(keyInfo);

        try {
            return joinPoint.proceed();
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Throwable t) {
            throw new RuntimeException("[分布式锁] - AOP调用异常", t);
        }
    }

    /**
     * 方法返回处理.
     *
     * @param joinPoint 切面方法
     * @param xLock     锁注解方法
     * @param result    方法返回值
     */
    @AfterReturning(value = "@annotation(xLock)", returning = "result")
    public void afterReturning(final JoinPoint joinPoint, final XLock xLock, final Object result) {
        if (log.isDebugEnabled()) {
            log.debug("[分布式锁] - 处理完：锁释放。返回值:{}", result);
        }
        KeyInfo keyInfo = xLockSpelResolver.getKeyInfo(joinPoint, xLock);
        unLock(keyInfo);
    }

    /**
     * 方法异常处理.
     *
     * @param joinPoint 切面方法
     * @param xLock     锁注解方法
     * @param ex        方法抛出异常
     * @throws Exception 方法抛出异常
     */
    @AfterThrowing(value = "@annotation(xLock)", throwing = "ex")
    public void afterThrowing(final JoinPoint joinPoint, final XLock xLock, final Exception ex) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("[分布式锁] - 内部异常：导致锁释放", ex);
        }
        KeyInfo keyInfo = xLockSpelResolver.getKeyInfo(joinPoint, xLock);
        unLock(keyInfo);
        throw ex;
    }

    /**
     * 清除线程上分布式锁.
     *
     * @param keyInfo keyInfo
     */
    private void unLock(final KeyInfo keyInfo) {
        LOCKED_HOLDER.get().get(keyInfo.getLockId()).getLockService().unlock(keyInfo);
        LOCKED_HOLDER.get().remove(keyInfo.getLockId());
        xLockSpelResolver.remove(keyInfo);
    }
}
