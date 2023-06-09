package com.cloud.rocketmq.utils;

import com.cloud.common.utils.RedisKeyUtil;
import com.cloud.common.utils.StringUtils;
import com.cloud.core.redis.RedisCache;
import com.cloud.rocketmq.base.BaseEvent;
import com.cloud.rocketmq.properties.RocketMQProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
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

    private static final String REDIS_REPEAT_PREFIX_KEY = "ROCKETMQ_PROCESS";
    private static final String ROCKETMQ_KEYS = RocketMQHeaders.PREFIX + RocketMQHeaders.KEYS;
    private static final String ROCKETMQ_TAGS = RocketMQHeaders.PREFIX + RocketMQHeaders.TAGS;
    private static final String ROCKETMQ_MESSAGE_ID = RocketMQHeaders.PREFIX + RocketMQHeaders.MESSAGE_ID;

    /**
     * 腾讯云延迟消息自定义属性名。对应的值应该是 标准毫秒化的时间戳
     */
    private static final String DELAY_SEND_TIME = "__STARTDELIVERTIME";

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
        return MessageBuilder.withPayload(event).setHeader(RocketMQHeaders.TAGS, tags).setHeader(RocketMQHeaders.KEYS, event.keys()).build();
    }

    /**
     * 构建延迟消息.
     *
     * @param event 事件对象
     * @param delayTime 延迟发送的时间戳
     * @param <T>   事件类型
     * @return T
     */
    public <T extends BaseEvent> Message<T> buildDelayMessage(final T event, final long delayTime) {
        if (StringUtils.isBlank(event.keys())) {
            throw new RuntimeException("keys是必填项");
        }

        String tags = StringUtils.defaultString(event.tags(), event.getClass().getSimpleName());
        return MessageBuilder.withPayload(event)
            //设置延迟发送的时间
            .setHeader(DELAY_SEND_TIME, delayTime)
            //设置tag和key
            .setHeader(RocketMQHeaders.TAGS, tags)
            .setHeader(RocketMQHeaders.KEYS, event.keys()).build();
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
        log.info("[MQ消息-生产消息][{}]--{}", topic, message);
        if (StringUtils.isBlank(properties.getNamespace())) {
            streamBridge.send(topic, message);
        } else {
            streamBridge.send(properties.getNamespace() + "%" +topic, message);
        }
    }

    /**
     * 生产消息，推送到mq
     *   兼容腾讯rocketmq的topic写法，自动带上namespace
     * @param topic topic
     * @param event 消息数据
     * @param delayTime 延迟发送的时间戳
     * @param <T> 消息数据泛型
     */
    public <T extends BaseEvent> void sendDelay(final String topic, final T event, final long delayTime) {
        Message<T> message = this.buildDelayMessage(event, delayTime);
        log.info("[MQ消息-生产延时消息][{},{}]--{}", topic, delayTime, message);
        if (StringUtils.isBlank(properties.getNamespace())) {
            streamBridge.send(topic, message);
        } else {
            streamBridge.send(properties.getNamespace() + "%" +topic, message);
        }
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
     * 获取消息Keys.
     *
     * @param event 事件对象
     * @return T
     */
    public String getMessageId(final Message<?> event) {
        String messageId = event.getHeaders().get(ROCKETMQ_MESSAGE_ID, String.class);
        if (StringUtils.isBlank(messageId)) {
            throw new RuntimeException("messageId不存在");
        }
        return messageId;
    }

    /**
     * 业务处理.
     *
     * @param event    需要消费的数据
     * @param function 方法体
     * @param <T>      实体类
     */
    public <T extends BaseEvent> void process(final Message<T> event, final Consumer<T> function) {
        process(RedisKeyUtil.generate(getTags(event), getKeys(event)), event.getPayload(), function);
    }

    /**
     * 业务处理.
     *
     * @param key      防重复消费缓存key
     * @param event    需要消费的数据
     * @param function 方法体
     * @param <T>      实体类
     */
    public <T> void process(final String key, final T event, final Consumer<T> function) {
        if (redisCache.setIfAbsent(RedisKeyUtil.generate(REDIS_REPEAT_PREFIX_KEY, key), "", 60, TimeUnit.MINUTES)) {
            try {
                log.info("[MQ消息-开始处理]--[{}]:{}", key, event);
                function.accept(event);
                log.info("[MQ消息-处理完成]--{}", key);
            } catch (Exception e) {
                log.error("[MQ处理异常]--[{}]", key, e);
                redisCache.deleteObject(key);
                throw e;
            }
        } else {
            log.info("[MQ消息-已处理]--[{}]:{}", key, event);
        }
    }
}
