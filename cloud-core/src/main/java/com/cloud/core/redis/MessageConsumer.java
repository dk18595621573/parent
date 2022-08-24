package com.cloud.core.redis;

import com.cloud.core.redis.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

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

    private RBlockingQueue<Message<T>> blockingQueue;

    public abstract String getGroup();

    @Override
    public void afterPropertiesSet() {
        blockingQueue = redissonClient.getBlockingQueue(String.format("MESSAGE:%s",getGroup()));
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

    protected void handleException(final T data, final Exception e) {
    }

    public abstract void consumer(T data);

    public void consumerFirst() {
        try {
            Message<T> msg = blockingQueue.poll(3, TimeUnit.SECONDS);
            consumer(msg.getData());
        } catch (Exception e) {
            log.error("消息队列手动消费异常【{}】:{}", getGroup(), e);
        }
    }
}
