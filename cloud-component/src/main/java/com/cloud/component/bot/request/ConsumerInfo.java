package com.cloud.component.bot.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 客户信息 企微好友.
 *
 * @author zenghao
 * @date 2022/8/30
 */
@NoArgsConstructor
@Data
public class ConsumerInfo {

    @JsonProperty("unionId")
    private String unionId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("externalUserId")
    private String externalUserId;
    @JsonProperty("gender")
    private Integer gender;
    @JsonProperty("type")
    private Integer type;
    @JsonProperty("followUser")
    private List<FollowUser> followUser;

    @NoArgsConstructor
    @Data
    public static class FollowUser {
        @JsonProperty("userId")
        private String userId;
        @JsonProperty("name")
        private String name;
        @JsonProperty("remark")
        private String remark;
        @JsonProperty("avatar")
        private String avatar;
        @JsonProperty("createTimestamp")
        private Long createTimestamp;
        @JsonProperty("state")
        private String state;
        @JsonProperty("tags")
        private List<Tags> tags;
        @JsonProperty("remarkMobiles")
        private List<String> remarkMobiles;

        @NoArgsConstructor
        @Data
        public static class Tags {
            @JsonProperty("groupName")
            private String groupName;
            @JsonProperty("tagName")
            private String tagName;
            @JsonProperty("type")
            private String type;
            @JsonProperty("tagId")
            private String tagId;
        }
    }

}
