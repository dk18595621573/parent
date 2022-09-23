package com.cloud.rocketmq.utils;

import com.cloud.common.utils.StringUtils;
import com.cloud.core.redis.RedisCache;
import com.cloud.core.utils.SpringUtils;
import com.cloud.rocketmq.base.BaseEvent;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
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
@UtilityClass
public class RocketMQUtils {
    private static final String KEYS = "KEYS";

    private static final String TAGS = "TAGS";

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
     * 业务处理.
     *
     * @param key      防重复消费缓存key
     * @param event    需要消费的数据
     * @param function 方法体
     * @param <T>      实体类
     */
    public static <T extends BaseEvent> void process(final String key, final T event, final Consumer<T> function) {
        RedisCache redisCache = SpringUtils.getBean(RedisCache.class);
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
