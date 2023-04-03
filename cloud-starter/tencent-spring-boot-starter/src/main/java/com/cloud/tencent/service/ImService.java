package com.cloud.tencent.service;

import cn.hutool.core.util.RandomUtil;
import com.cloud.tencent.exception.TencentException;
import com.cloud.tencent.properties.ImProperties;
import com.tencentcloudapi.im.ApiClient;
import com.tencentcloudapi.im.ApiException;
import com.tencentcloudapi.im.api.AccountApi;
import com.tencentcloudapi.im.api.GroupApi;
import com.tencentcloudapi.im.model.CommonResponse;
import com.tencentcloudapi.im.model.CreateGroupRequest;
import com.tencentcloudapi.im.model.CreateGroupResponse;
import com.tencentcloudapi.im.model.DestroyGroupRequest;
import com.tencentcloudapi.im.model.MultiAccountImportRequest;
import com.tencentcloudapi.im.model.MultiAccountImportResponse;
import com.tencentyun.TLSSigAPIv2;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * IM服务.
 *
 * @author Luo
 * @date 2023-3-27 13:54:54
 */
@Slf4j
public class ImService {

    private final ApiClient apiClient;

    private final ImProperties imProperties;

    private final AccountApi accountApi = new AccountApi();

    private final GroupApi groupApi = new GroupApi();

    public ImService(final ApiClient apiClient, final ImProperties imProperties) {
        this.apiClient = apiClient;
        this.imProperties = imProperties;
    }

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

    /**
     * 导入多个帐号.
     * 本接口单次最多支持导入100个帐号，同一个帐号重复导入仅会创建1个内部 ID。
     * 本接口不支持导入帐号的昵称和头像信息，您可以调用 设置资料 接口设置昵称和头像等信息。
     *
     * @param accounts 账号集合
     */
    public List<String> multiAccountImport(final List<String> accounts) {
        MultiAccountImportRequest request = new MultiAccountImportRequest();
        request.setAccounts(accounts);
        try {
            MultiAccountImportResponse response = accountApi.multiAccountImport(getRandom(), request);
            return response.getFailAccounts();
        } catch (ApiException e) {
            throw new TencentException(e.getMessage(), e.getCode());
        }
    }

    /**
     * 创建群组.
     *
     */
    public void createGroup() {
        CreateGroupRequest request = new CreateGroupRequest();

        try {
            CreateGroupResponse response = groupApi.createGroup(getRandom(), request);

        } catch (ApiException e) {
            throw new TencentException(e.getMessage(), e.getCode());
        }
    }

    /**
     * 解散群组.
     *
     */
    public void destroyGroup() {
        DestroyGroupRequest request = new DestroyGroupRequest();

        try {
            CommonResponse response = groupApi.destroyGroup(getRandom(), request);

        } catch (ApiException e) {
            throw new TencentException(e.getMessage(), e.getCode());
        }
    }

    /**
     * 获取随机数.
     *
     * @return 随机数
     */
    private Integer getRandom() {
        return RandomUtil.randomInt(0, 999999999);
    }

}
