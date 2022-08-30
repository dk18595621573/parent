package com.cloud.component.bot.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 企微机器人事件.
 *
 * @author zenghao
 * @date 2022/8/30
 */
@Data
@NoArgsConstructor
public class BotEvent<T> implements Serializable {

    @JsonProperty("type")
    private String type;
    @JsonProperty("event")
    private Event event;
    @JsonProperty("data")
    private T data;
    @JsonProperty("timestamp")
    private Long timestamp;

    @NoArgsConstructor
    @Data
    public static class Event {
        @JsonProperty("ToUserName")
        private String toUserName;
        @JsonProperty("FromUserName")
        private String fromUserName;
        @JsonProperty("CreateTime")
        private String createTime;
        @JsonProperty("MsgType")
        private String msgType;
        @JsonProperty("Event")
        private String event;
        @JsonProperty("ChangeType")
        private String changeType;
        @JsonProperty("UserID")
        private String userID;
        @JsonProperty("ExternalUserID")
        private String externalUserID;
        @JsonProperty("State")
        private String state;
        @JsonProperty("WelcomeCode")
        private String welcomeCode;
    }
}
