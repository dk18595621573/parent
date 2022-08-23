package com.cloud.core.redis;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * redis延迟队列实现.
 *
 * @author zenghao
 * @date 2022/6/23
 */
@Slf4j
public abstract class DelayedTask<T> implements InitializingBean {

    @Autowired
    private RedissonClient redissonClient;

    private RDelayedQueue<T> delayedQueue;

    private RBlockingQueue<T> blockingQueue;

    /**
     * 任务分组名
     * @return 任务分组名
     */
    public abstract String getTaskGroup();

    /**
     * 消费数据
     * @param data 任务数据
     */
    public abstract void consumer(T data);

    @Override
    public void afterPropertiesSet() {
        blockingQueue = redissonClient.getBlockingQueue(getTaskGroup());
        delayedQueue = redissonClient.getDelayedQueue(blockingQueue);
        blockingQueue.subscribeOnElements(data -> {
            try {
                log.info("延时队列开始消费【{}】:{}", getTaskGroup(), data);
                consumer(data);
                log.info("延时队列完成消费【{}】:{}", getTaskGroup(), data);
            } catch (Exception e) {
                log.error("延时队列消费异常【{}】【{}】:", getTaskGroup(), data, e);
                handleException(data, e);
            }
        });
    }

    /**
     * 生产消息
     * @param data 消息数据
     * @param delay 延迟时间 单位：秒
     */
    public void producer(T data, long delay) {
        log.debug("延时队列【{}】插入延时[{}秒]数据:{}", getTaskGroup(), delay, data);
        delayedQueue.offerAsync(data, delay, TimeUnit.SECONDS);
    }

    /**
     * 处理异常
     * @param data 消费数据
     * @param e 异常信息
     */
    protected void handleException(T data, Exception e) {
        //do something for handle exception
    }
}
