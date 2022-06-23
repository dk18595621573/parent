package com.cloud.xlock.config;

import com.cloud.xlock.XLockInterceptor;
import com.cloud.xlock.XLockSpelResolver;
import com.cloud.xlock.annotation.EnableXLock;
import com.cloud.xlock.lock.FairLock;
import com.cloud.xlock.lock.LockFactory;
import com.cloud.xlock.lock.MultiLock;
import com.cloud.xlock.lock.ReadLock;
import com.cloud.xlock.lock.RedLock;
import com.cloud.xlock.lock.ReentrantLock;
import com.cloud.xlock.lock.WriteLock;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配.
 *
 * @author breggor
 */
@Configuration
@ConditionalOnBean(annotation = EnableXLock.class)
public class XLockAutoConfiguration {

    /**
     * 锁服务工厂.
     *
     * @return 锁服务工厂
     */
    @Bean
    @ConditionalOnMissingBean
    public LockFactory serviceBeanFactory() {
        return new LockFactory();
    }

    /**
     * 可重入锁加锁服务.
     *
     * @return 可重入锁加锁服务.
     */
    @Bean
    @ConditionalOnMissingBean
    public ReentrantLock reentrantLock() {
        return new ReentrantLock();
    }

    /**
     * 公平锁操作服务.
     *
     * @return 公平锁操作服务
     */
    @Bean
    @ConditionalOnMissingBean
    public FairLock fairLock() {
        return new FairLock();
    }

    /**
     * 联锁操作服务.
     *
     * @return 联锁操作服务
     */
    @Bean
    @ConditionalOnMissingBean
    public MultiLock multiLock() {
        return new MultiLock();
    }

    /**
     * 红锁操作服务.
     *
     * @return 红锁操作服务
     */
    @Bean
    @ConditionalOnMissingBean
    public RedLock redLock() {
        return new RedLock();
    }

    /**
     * 读锁操作服务.
     *
     * @return 读锁操作服务
     */
    @Bean
    @ConditionalOnMissingBean
    public ReadLock readLock() {
        return new ReadLock();
    }

    /**
     * 写锁操作服务.
     *
     * @return 写锁操作服务
     */
    @Bean
    @ConditionalOnMissingBean
    public WriteLock writeLock() {
        return new WriteLock();
    }

    /**
     * spring el解析key策略.
     *
     * @return spring el解析key策略
     */
    @Bean
    @ConditionalOnMissingBean
    public XLockSpelResolver xLockSpelResolver() {
        return new XLockSpelResolver();
    }

    /**
     * 锁拦截器.
     *
     * @return 锁拦截器
     */
    @Bean
    @ConditionalOnMissingBean
    public XLockInterceptor xLockInterceptor() {
        return new XLockInterceptor();
    }

}
