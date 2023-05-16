package com.cloud.shadow.bean.response.token;

import com.cloud.shadow.bean.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 影刀RPA 鉴权 响应参数.
 *
 * @author Luo
 * @since 2023-5-16 11:51
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TokenCreateResponse extends BaseResponse implements Serializable {

    private static final long serialVersionUID = 2812778613524018744L;

    /**
     * token.
     * accessToken的最大有效期是2小时。每次请求鉴权接口会生成一个新的token接口，accessToken是一个临时token，调用方可以不用缓存，任务运行完成就会失效。
     */
    private String accessToken;

    /**
     * 过期时间.
     */
    private Integer expiresIn;

}
