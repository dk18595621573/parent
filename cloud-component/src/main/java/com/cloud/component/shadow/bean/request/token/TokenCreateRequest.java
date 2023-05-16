package com.cloud.component.shadow.bean.request.token;

import com.cloud.component.shadow.bean.request.BaseShadowBotRequest;
import com.cloud.component.shadow.bean.response.token.TokenCreateResponse;
import com.cloud.component.shadow.consts.ShadowBotEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 影刀RPA 鉴权 请求参数.
 *
 * @author Luo
 * @date 2023-05-16 14:16
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TokenCreateRequest extends BaseShadowBotRequest<TokenCreateResponse> implements Serializable {

    private static final long serialVersionUID = -5191353816956462227L;

    /**
     * 密钥Key.
     */
    private String accessKeyId;

    /**
     * 密钥Secret.
     */
    private String accessKeySecret;

    /**
     * 获取API请求地址.
     *
     * @return API请求地址
     */
    @Override
    public String getApiUrl() {
        return ShadowBotEnum.Url.TOKEN_CREATE.getCode();
    }


}
