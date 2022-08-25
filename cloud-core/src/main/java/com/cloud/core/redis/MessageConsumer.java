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

    private RBlockingQueue<Message<T>> blockingQueue;

    private int listenerId;

    public abstract String getGroup();

    @Override
    public void afterPropertiesSet() {
        blockingQueue = redissonClient.getBlockingQueue(String.format("MESSAGE:%s",getGroup()));
        listenerId = subscribe();
    }

    protected void handleException(final T data, final Exception e) {
    }

    public abstract void consumer(T data);

    private int subscribe() {
        return blockingQueue.subscribeOnElements(m -> {
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

    public int reSubscribe() {
        int oldId = this.listenerId;
        blockingQueue.unsubscribe(oldId);
        this.listenerId = subscribe();
        log.info("消息队列重新订阅[{}]: 【{} -> {}】", getGroup(), oldId, listenerId);
        return this.listenerId;
    }
}
