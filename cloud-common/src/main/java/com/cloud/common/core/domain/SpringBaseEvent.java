package com.cloud.common.core.domain;

import org.springframework.context.ApplicationEvent;

import java.io.Serializable;

/**
 * 事件通知基础类 .
 *
 * @param <T> 泛型T
 * @author tianzonghao
 * @date 2021-06-21 15:21
 */
public abstract class SpringBaseEvent<T extends Serializable> extends ApplicationEvent {

    private static final long serialVersionUID = 6336203226054907094L;

    protected T data;

    public SpringBaseEvent(final Object source, final T data) {
        super(source);
        this.data = data;
    }

    public SpringBaseEvent(final T data) {
        super(data);
        this.data = data;
    }

    public T getData() {
        return data;
    }

}
