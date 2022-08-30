package com.cloud.component.bot.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


/**
 * 企微机器人通讯录联系人信息.
 *
 * @author zenghao
 * @date 2022/8/11
 */
@NoArgsConstructor
@Data
public class Contact implements Serializable {

    @JsonProperty("chatId")
    private String chatId;
    @JsonProperty("botInfo")
    private BotInfo botInfo;
    @JsonProperty("wxid")
    private String wxid;
    @JsonProperty("weixin")
    private String weixin;
    @JsonProperty("nickName")
    private String nickName;
    @JsonProperty("alias")
    private String alias;
    @JsonProperty("avatarUrl")
    private String avatarUrl;
    @JsonProperty("city")
    private String city;
    @JsonProperty("province")
    private String province;
    @JsonProperty("gender")
    private Integer gender;
    @JsonProperty("labels")
    private List<Label> labels;
    @JsonProperty("contactType")
    private Integer contactType;
    @JsonProperty("coworker")
    private Boolean coworker;
    @JsonProperty("unionId")
    private String unionId;
    @JsonProperty("externalUserId")
    private String externalUserId;
    @JsonProperty("deleted")
    private Boolean deleted;

    @NoArgsConstructor
    @Data
    public static class BotInfo {
        @JsonProperty("wxid")
        private String wxid;
        @JsonProperty("weixin")
        private String weixin;
        @JsonProperty("nickName")
        private String nickName;
    }

    @NoArgsConstructor
    @Data
    public static class Label {
        @JsonProperty("id")
        private String id;
        @JsonProperty("name")
        private String name;
    }
}
