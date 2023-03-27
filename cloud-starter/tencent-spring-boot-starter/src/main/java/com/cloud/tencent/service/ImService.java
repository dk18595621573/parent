package com.cloud.tencent.service;

import com.cloud.tencent.properties.ImProperties;
import com.tencentcloudapi.im.ApiClient;
import com.tencentyun.TLSSigAPIv2;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * IM服务.
 *
 * @author Luo
 * @date 2023-3-27 13:54:54
 */
@Slf4j
@AllArgsConstructor
public class ImService {

    private final ApiClient apiClient;

    private final ImProperties imProperties;

    /**
     * 获取用户签名.
     *
     * @param userId 用户Id
     * @return 签名
     */
    public String getUserSig(final String userId) {
        return getUserSig(userId, 30 * 86400);
    }

    /**
     * 获取用户签名.
     *
     * @param userId 用户Id
     * @param expire 过期时间
     * @return 签名
     */
    public String getUserSig(final String userId, long expire) {
        TLSSigAPIv2 tlsSigApi = new TLSSigAPIv2(imProperties.getSdkAppId(), imProperties.getSecretKey());
        return tlsSigApi.genUserSig(userId, expire);
    }


}
