package com.cloud.component.bot.request;

import com.cloud.component.bot.message.MessagePayload;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * TODO auto.
 *
 * @author zenghao
 * @date 2022/8/30
 */
@NoArgsConstructor
@Data
public class ConsumerMessage implements Serializable {

    @JsonProperty("data")
    private MessageInfo data;

    @NoArgsConstructor
    @Data
    public static class MessageInfo {
        @JsonProperty("messageId")
        private String messageId;
        @JsonProperty("chatId")
        private String chatId;
        @JsonProperty("avatar")
        private String avatar;
        @JsonProperty("roomTopic")
        private String roomTopic;
        @JsonProperty("roomId")
        private String roomId;
        @JsonProperty("contactName")
        private String contactName;
        @JsonProperty("contactId")
        private String contactId;
        @JsonProperty("payload")
        private MessagePayload payload;
        @JsonProperty("type")
        private Integer type;
        @JsonProperty("timestamp")
        private Long timestamp;
        @JsonProperty("token")
        private String token;
        @JsonProperty("contactType")
        private Integer contactType;
        @JsonProperty("coworker")
        private Boolean coworker;
        @JsonProperty("botId")
        private String botId;
        @JsonProperty("botWxid")
        private String botWxid;
        @JsonProperty("botWeixin")
        private String botWeixin;
        @JsonProperty("isSelf")
        private Boolean isSelf;
    }
}
