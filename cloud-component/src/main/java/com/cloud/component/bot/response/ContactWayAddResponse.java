package com.cloud.component.bot.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 渠道二维码创建响应结果.
 *
 * @author zenghao
 * @date 2022/8/31
 */
@Data
@NoArgsConstructor
public class ContactWayAddResponse extends ApiResponse {

    @JsonProperty("config_id")
    private String configId;

    @JsonProperty("qr_code")
    private String qrCode;
}
