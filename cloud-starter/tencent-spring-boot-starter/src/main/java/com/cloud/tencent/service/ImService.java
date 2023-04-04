package com.cloud.tencent.service;

import cn.hutool.core.util.RandomUtil;
import com.cloud.tencent.exception.TencentException;
import com.cloud.tencent.properties.ImProperties;
import com.tencentcloudapi.im.ApiException;
import com.tencentcloudapi.im.api.AccountApi;
import com.tencentcloudapi.im.api.GroupApi;
import com.tencentcloudapi.im.api.MuteApi;
import com.tencentcloudapi.im.api.OperationApi;
import com.tencentcloudapi.im.api.PortraitApi;
import com.tencentcloudapi.im.api.RecentContactApi;
import com.tencentcloudapi.im.api.SingleChatApi;
import com.tencentcloudapi.im.model.AddGroupMemberRequest;
import com.tencentcloudapi.im.model.AddGroupMemberResponse;
import com.tencentcloudapi.im.model.CommonResponse;
import com.tencentcloudapi.im.model.CreateGroupRequest;
import com.tencentcloudapi.im.model.CreateGroupResponse;
import com.tencentcloudapi.im.model.DeleteGroupMemberRequest;
import com.tencentcloudapi.im.model.DestroyGroupRequest;
import com.tencentcloudapi.im.model.MultiAccountImportRequest;
import com.tencentcloudapi.im.model.MultiAccountImportResponse;
import com.tencentcloudapi.im.model.PortraitSetRequest;
import com.tencentcloudapi.im.model.PortraitSetResponse;
import com.tencentyun.TLSSigAPIv2;
import lombok.extern.slf4j.Slf4j;

/**
 * IM客户端服务.
 *
 * @author Luo
 * @date 2023-3-27 13:54:54
 */
@Slf4j
public class ImService {

    private final ImProperties imProperties;

    // 消息相关
    private final SingleChatApi singleChatApi = new SingleChatApi();
    // 会话相关
    private final RecentContactApi recentContactApi = new RecentContactApi();
    // 账号管理
    private final AccountApi accountApi = new AccountApi();
    // 用户资料
    private final PortraitApi portraitApi = new PortraitApi();
    // 群组管理
    private final GroupApi groupApi = new GroupApi();
    // 全局禁言管理
    private final MuteApi muteApi = new MuteApi();
    // 运营管理
    private final OperationApi operationApi = new OperationApi();

    public ImService(final ImProperties imProperties) {
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
     *
     * @param request 请求
     * @return 结果
     */
    public MultiAccountImportResponse multiAccountImport(final MultiAccountImportRequest request) {
        try {
            return accountApi.multiAccountImport(getRandom(), request);
        } catch (ApiException e) {
            throw new TencentException(e.getMessage(), e.getCode());
        }
    }

    /**
     * 设置资料.
     *
     * @param request 请求
     * @return 结果
     */
    public PortraitSetResponse portraitSet(final PortraitSetRequest request) {
        try {
            return portraitApi.portraitSet(getRandom(), request);
        } catch (ApiException e) {
            throw new TencentException(e.getMessage(), e.getCode());
        }
    }

    /**
     * 创建群组.
     *
     * @param request 请求
     * @return 结果
     */
    public CreateGroupResponse createGroup(final CreateGroupRequest request) {
        try {
            return groupApi.createGroup(getRandom(), request);
        } catch (ApiException e) {
            throw new TencentException(e.getMessage(), e.getCode());
        }
    }

    /**
     * 增加群成员.
     *
     * @param request 请求
     * @return 结果
     */
    public AddGroupMemberResponse addGroupMember(final AddGroupMemberRequest request) {
        try {
            return groupApi.addGroupMember(getRandom(), request);
        } catch (ApiException e) {
            throw new TencentException(e.getMessage(), e.getCode());
        }
    }

    /**
     * 删除群成员.
     *
     * @param request 请求
     * @return 结果
     */
    public CommonResponse deleteGroupMember(final DeleteGroupMemberRequest request) {
        try {
            return groupApi.deleteGroupMember(getRandom(), request);
        } catch (ApiException e) {
            throw new TencentException(e.getMessage(), e.getCode());
        }
    }

    /**
     * 解散群组.
     *
     * @param request 请求
     * @return 结果
     */
    public CommonResponse destroyGroup(final DestroyGroupRequest request) {
        try {
            return groupApi.destroyGroup(getRandom(), request);
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
