package com.cloud.rocketmq.message;

import com.cloud.common.constant.Constants;
import com.cloud.common.utils.StringUtils;
import com.cloud.rocketmq.base.BaseEvent;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.util.Objects;

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
     * 指定客户端
     */
    private String client;

    /**
     * 推送数据
     */
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
    private T data;

    public static <T> SseMessage<T> ofData(final T data) {
        SseMessage<T> sseMessage = new SseMessage<>();
        sseMessage.setData(data);
        return sseMessage;
    }

    public static <T> SseMessage<T> ofSuffix(final String[] suffix, final T data) {
        SseMessage<T> sseMessage = new SseMessage<>();
        sseMessage.setData(data);
        sseMessage.setSuffix(suffix);
        return sseMessage;
    }

    public static <T> SseMessage<T> ofNotSuffix(final String[] notSuffix, final T data) {
        SseMessage<T> sseMessage = new SseMessage<>();
        sseMessage.setData(data);
        sseMessage.setNotSuffix(notSuffix);
        return sseMessage;
    }

    public static <T> SseMessage<T> ofClient(final String client, final T data) {
        SseMessage<T> sseMessage = new SseMessage<>();
        sseMessage.setData(data);
        sseMessage.setClient(client);
        return sseMessage;
    }

    @Override
    public String keys() {
        String keys = super.keys();
        if (StringUtils.isBlank(keys)) {
            return Constants.snowflake.nextIdStr();
        }
        return keys;
    }

    @Override
    public String tags() {
        String tags = super.tags();
        if (StringUtils.isBlank(tags)) {
            return Objects.isNull(getData()) ? "*" : getData().getClass().getSimpleName();
        }
        return tags;
    }
}
