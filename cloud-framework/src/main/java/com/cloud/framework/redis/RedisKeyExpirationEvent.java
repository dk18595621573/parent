package com.cloud.framework.redis;

import com.cloud.common.utils.spring.SpringBaseEvent;
import lombok.Getter;

/**
 * redis key 过期事件.
 *
 * @author zenghao
 * @date 2022/5/17
 */
@Getter
public class RedisKeyExpirationEvent extends SpringBaseEvent<String> {

    /**
     * 业务模块.
     */
    private final String module;

    public RedisKeyExpirationEvent(Object source, String module, String data) {
        super(source, data);
        this.module = module;
    }

    public String getKey() {
        return this.data;
    }
}