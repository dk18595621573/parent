package com.cloud.framework.redis;

import com.cloud.common.utils.RedisKeyUtil;
import com.cloud.common.utils.spring.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyspaceEventMessageListener;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * 必须先开启监听.
 * cloud.redis.listener.enabled=true
 *
 * @author zenghao
 * @date 2022/5/17
 */
@Slf4j
@Component
@ConditionalOnProperty(prefix = "cloud.redis.listener", name = "enabled", havingValue = "true")
public class RedisKeyExpirationListener extends KeyspaceEventMessageListener {

    @Autowired
    private RedisProperties redisProperties;

    public RedisKeyExpirationListener(final RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    protected void doRegister(final RedisMessageListenerContainer listenerContainer) {
        String topicName = String.format("__keyevent@%s__:expired", redisProperties.getDatabase());
        log.info("start listener redis topic :{}", topicName);
        listenerContainer.addMessageListener(this, new PatternTopic(topicName));
    }

    @Override
    protected void doHandleMessage(final Message message) {
        log.debug("监听到redisKey过期事件:{}", message);
        String[] keys = RedisKeyUtil.split(message.toString());
        if (ArrayUtils.isEmpty(keys) || !RedisKeyUtil.EXPIRATION_PREFIX.equals(keys[0])) {
            return;
        }
        int len = keys.length;
        String module = (len > 2) ? keys[len - 2] : null;
        SpringUtils.publishEvent(new RedisKeyExpirationEvent(this, module, keys[len - 1]));
    }

}
