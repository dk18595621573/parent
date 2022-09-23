package com.cloud.rocketmq.base;

import java.io.Serializable;

/**
 * 事件基础类：keys与tags不在消息体内，不为作为唯一键校验.
 *
 * @author breggor
 * @date 2020-12-30
 */
public abstract class BaseEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * key标识：不在消息体内.
     */
    protected String keys;

    /**
     * tags标签：不在消息体内.
     */
    protected String tags = "*";

    /**
     * 返回keys.
     *
     * @return String
     */
    public final String keys() {
        return keys;
    }

    /**
     * 设置keys.
     *
     * @param keys keys
     * @return BaseEvent
     */
    public BaseEvent keys(final String keys) {
        this.keys = keys;
        return this;
    }

    /**
     * 返回tags.
     *
     * @return String
     */
    public final String tags() {
        return tags;
    }

    /**
     * 设置tags.
     *
     * @param tags tags
     * @return BaseEvent
     */
    public BaseEvent tags(final String tags) {
        this.tags = tags;
        return this;
    }
}
