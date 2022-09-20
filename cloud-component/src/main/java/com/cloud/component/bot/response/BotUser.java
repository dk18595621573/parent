package com.cloud.component.bot.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 企微托管机器人信息.
 *
 * @author zenghao
 * @date 2022/8/16
 */
@NoArgsConstructor
@Data
public class BotUser implements Serializable {

    @JsonProperty("nickName")
    private String nickName;
    @JsonProperty("wxid")
    private String wxid;
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("weixin")
    private String weixin;
    @JsonProperty("online")
    private Boolean online;
    @JsonProperty("id")
    private String id;
}
