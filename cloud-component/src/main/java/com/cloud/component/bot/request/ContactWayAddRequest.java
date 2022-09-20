package com.cloud.component.bot.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 渠道二维码创建请求.
 *
 * @author zenghao
 * @date 2022/8/31
 */
@NoArgsConstructor
@Data
public class ContactWayAddRequest implements BaseRequest {

    public static final int TYPE_SINGLE = 1;

    public static final int SCENE_QRCODE = 2;

    @JsonProperty("type")
    private Integer type;
    @JsonProperty("scene")
    private Integer scene;
    @JsonProperty("state")
    private String state;
    @JsonProperty("user")
    private List<String> user;

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("type", getType());
        map.put("scene", getScene());
        map.put("user", getUser());
        map.put("state", getState());
        return map;
    }
}
