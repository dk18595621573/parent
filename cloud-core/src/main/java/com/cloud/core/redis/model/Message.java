package com.cloud.core.redis.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 队列消息.
 *
 * @author zenghao
 * @date 2022/7/14
 */
@Data
public class Message<T> implements Serializable {

    private String msgId;

    private T data;
}
