package com.cloud.rocketmq.message;

import com.cloud.rocketmq.base.BaseEvent;
import lombok.Data;

/**
 * SSE推送事件.
 *
 * @author zenghao
 * @date 2022/9/23
 */
@Data
public class SseMessage<T> extends BaseEvent {

    /**
     * 指定客户端后缀
     */
    private String[] suffix;
    /**
     * 指定非客户端后缀
     */
    private String[] notSuffix;

    /**
     * 推送数据
     */
    private T data;
}
