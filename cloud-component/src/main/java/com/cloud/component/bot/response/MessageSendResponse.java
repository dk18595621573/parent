package com.cloud.component.bot.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 企业级消息管理接口 发送消息 响应数据.
 *
 * @author Luo
 * @date 2023-03-26 10:52
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MessageSendResponse extends ApiResponse {

    private static final long serialVersionUID = -5711853175759649932L;

    @JsonProperty("requestId")
    private String requestId;

}

