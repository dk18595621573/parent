package com.cloud.core.redis;

import com.cloud.core.redis.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 消息消费者.
 *
 * @author zenghao
 * @date 2022/7/14
 */
@Slf4j
public abstract class MessageConsumer<T> implements InitializingBean {

    @Autowired
    private RedissonClient redissonClient;

    public abstract String getGroup();

    @Override
    public void afterPropertiesSet() {
        RBlockingQueue<Message<T>> blockingQueue = redissonClient.getBlockingQueue(String.format("MESSAGE:%s",getGroup()));
        blockingQueue.subscribeOnElements(m -> {
            try {
                log.info("消息队列开始消费:[{} -> {}]【{}】", getGroup(), m.getMsgId(), m.getData());
                consumer(m.getData());
                log.info("消息队列消费完成:[{} -> {}]【{}】", getGroup(), m.getMsgId(), m.getData());
            } catch (Exception e) {
                log.error("消息队列消费失败:[{}]【{}】", m.getMsgId(), m.getData(), e);
                handleException(m.getData(), e);
            }
        });
    }

    private void handleException(final T data, final Exception e) {
    }

    public abstract void consumer(T data);
}
