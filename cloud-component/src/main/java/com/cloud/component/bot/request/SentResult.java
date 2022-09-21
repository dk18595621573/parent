package com.cloud.component.bot.request;

import com.cloud.component.bot.message.MessagePayload;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 回调.
 *
 * @author zenghao
 * @date 2022/9/21
 */
@NoArgsConstructor
@Data
public class SentResult implements Serializable {

    @JsonProperty("requestId")
    private String requestId;
    @JsonProperty("sentStatus")
    private Boolean sentStatus;
    @JsonProperty("token")
    private String token;
    @JsonProperty("errorCode")
    private Integer errorCode;
    @JsonProperty("errorMsg")
    private String errorMsg;
    @JsonProperty("chatId")
    private String chatId;
    @JsonProperty("sendTimestamp")
    private Long sendTimestamp;
    @JsonProperty("messageType")
    private Integer messageType;
    @JsonProperty("payload")
    private MessagePayload payload;
    @JsonProperty("externalRequestId")
    private String externalRequestId;
}
