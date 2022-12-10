package com.cloud.component.bot.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取会话id响应结果.
 *
 * @author zenghao
 * @date 2022/12/8
 */
@Data
@NoArgsConstructor
public class ChatResponse extends ApiResponse {

    @JsonProperty("chatId")
    private String chatId;
}
