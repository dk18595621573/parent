package com.cloud.core.redis;

import com.cloud.common.utils.uuid.IdUtils;
import com.cloud.core.redis.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 消息生产者.
 *
 * @author zenghao
 * @date 2022/7/14
 */
@Slf4j
public abstract class MessageProducer<T> implements InitializingBean {

    @Autowired
    private RedissonClient redissonClient;

    private RBlockingQueue<Message<T>> blockingQueue;

    public abstract String getGroup();

    @Override
    public void afterPropertiesSet() {
        blockingQueue = redissonClient.getBlockingQueue(String.format("MESSAGE:%s",getGroup()));
    }

    public void publish(T data) {
        final String uuid = IdUtils.simpleUUID();
        log.info("消息队列开始生产:[{} -> {}]【{}】", getGroup(), uuid, data);
        Message<T> msg = new Message<>();
        msg.setMsgId(uuid);
        msg.setData(data);
        blockingQueue.putAsync(msg);
    }
}
