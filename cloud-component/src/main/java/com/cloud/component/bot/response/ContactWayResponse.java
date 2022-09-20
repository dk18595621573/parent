package com.cloud.component.bot.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 获取渠道二维码信息结果.
 *
 * @author zenghao
 * @date 2022/8/31
 */
@NoArgsConstructor
@Data
public class ContactWayResponse extends ApiResponse {

    @JsonProperty("contact_way")
    private ContactWay contactWay;

    @NoArgsConstructor
    @Data
    public static class ContactWay {
        @JsonProperty("config_id")
        private String configId;
        @JsonProperty("type")
        private Integer type;
        @JsonProperty("scene")
        private Integer scene;
        @JsonProperty("style")
        private Integer style;
        @JsonProperty("remark")
        private String remark;
        @JsonProperty("skip_verify")
        private Boolean skipVerify;
        @JsonProperty("state")
        private String state;
        @JsonProperty("qr_code")
        private String qrCode;
        @JsonProperty("user")
        private List<String> user;
        @JsonProperty("party")
        private List<Integer> party;
        @JsonProperty("is_temp")
        private Boolean isTemp;
        @JsonProperty("expires_in")
        private Integer expiresIn;
        @JsonProperty("chat_expires_in")
        private Integer chatExpiresIn;
        @JsonProperty("unionid")
        private String unionid;
    }

}
