package com.cloud.component.bot.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 发消息接口响应结果.
 *
 * @author zenghao
 * @date 2022/9/21
 */
@Data
public class MessageResponse implements Serializable {

    @JsonProperty("requestId")
    private String requestId;
}
