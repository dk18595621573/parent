package com.cloud.rocketmq.utils;

import com.cloud.common.utils.StringUtils;
import com.cloud.core.redis.RedisCache;
import com.cloud.rocketmq.base.BaseEvent;
import com.cloud.rocketmq.properties.RocketMQProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * rocketmq工具类.
 *
 * @author breggor
 */
@Slf4j
@AllArgsConstructor
public class RocketMQBuilder {

    private static final String PREFIX = "rocketmq_";
    private static final String KEYS = "KEYS";
    private static final String TAGS = "TAGS";

    private static final String ROCKETMQ_KEYS = PREFIX + KEYS;
    private static final String ROCKETMQ_TAGS = PREFIX + TAGS;

    private final RocketMQProperties properties;

    private final StreamBridge streamBridge;

    private final RedisCache redisCache;

    /**
     * 构建消息.
     *
     * @param event 事件对象
     * @param <T>   事件类型
     * @return T
     */
    public <T extends BaseEvent> Message<T> buildMessage(final T event) {
        if (StringUtils.isBlank(event.keys())) {
            throw new RuntimeException("keys是必填项");
        }
        String tags = StringUtils.defaultString(event.tags(), event.getClass().getSimpleName());
        return MessageBuilder.withPayload(event).setHeader(TAGS, tags).setHeader(KEYS, event.keys()).build();
    }

    /**
     * 生产消息，推送到mq
     *   兼容腾讯rocketmq的topic写法，自动带上namespace
     * @param topic topic
     * @param event 消息数据
     * @param <T> 消息数据泛型
     */
    public <T extends BaseEvent> void send(final String topic, final T event) {
        Message<T> message = buildMessage(event);
        if (StringUtils.isBlank(properties.getNamespace())) {
            streamBridge.send(topic, message);
        } else {
            streamBridge.send(properties.getNamespace() + "%" +topic, message);
        }
        log.info("[MQ消息-生产消息]--{}:", message);
    }

    /**
     * 获取消息Keys.
     *
     * @param event 事件对象
     * @return T
     */
    public String getKeys(final Message<?> event) {
        String keys = event.getHeaders().get(ROCKETMQ_KEYS, String.class);
        if (StringUtils.isBlank(keys)) {
            throw new RuntimeException("keys不存在");
        }
        return keys;
    }

    /**
     * 获取消息Keys.
     *
     * @param event 事件对象
     * @return T
     */
    public String getTags(final Message<?> event) {
        String tags = event.getHeaders().get(ROCKETMQ_TAGS, String.class);
        if (StringUtils.isBlank(tags)) {
            throw new RuntimeException("tags不存在");
        }
        return tags;
    }

    /**
     * 业务处理.
     *
     * @param event    需要消费的数据
     * @param function 方法体
     * @param <T>      实体类
     */
    public <T extends BaseEvent> void process(final Message<T> event, final Consumer<T> function) {
        process(getKeys(event), event.getPayload(), function);
    }

    /**
     * 业务处理.
     *
     * @param key      防重复消费缓存key
     * @param event    需要消费的数据
     * @param function 方法体
     * @param <T>      实体类
     */
    public <T extends BaseEvent> void process(final String key, final T event, final Consumer<T> function) {
        if (redisCache.setIfAbsent(key, "", 60, TimeUnit.MINUTES)) {
            try {
                log.info("[MQ消息-开始处理]--[{}]:{}", key, event);
                function.accept(event);
                log.info("[MQ消息-处理完成]--{}", key);
            } catch (Exception e) {
                log.error("[MQ处理异常]--[{}]:", key, e);
                redisCache.deleteObject(key);
                throw e;
            }
        } else {
            log.info("[MQ消息-已处理]--[{}]:{}", key, event);
        }
    }
}
