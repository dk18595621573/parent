package com.cloud.component.bot.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 秒回数据同步.
 *
 * @author zenghao
 * @date 2022/8/30
 */
@NoArgsConstructor
@Data
public class SyncConsumerInfo {

    @JsonProperty("chatId")
    private String chatId;
    @JsonProperty("unionId")
    private String unionId;
    @JsonProperty("externalUserId")
    private String externalUserId;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("wxid")
    private String wxid;
}
