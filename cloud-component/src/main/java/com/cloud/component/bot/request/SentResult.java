package com.cloud.component.bot.request;

import cn.hutool.core.util.ArrayUtil;
import com.cloud.component.bot.message.MessagePayload;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 回调.
 *
 * @author zenghao
 * @date 2022/9/21
 */
@NoArgsConstructor
@Data
public class SentResult implements Serializable {

    private static final Integer[] DELETED_STATUS = {153, 157,158,159};

    @JsonProperty("requestId")
    private String requestId;
    @JsonProperty("sentStatus")
    private Boolean sentStatus;
    @JsonProperty("token")
    private String token;
    @JsonProperty("errorCode")
    private Integer errorCode;
    @JsonProperty("errorMsg")
    private String errorMsg;
    @JsonProperty("chatId")
    private String chatId;
    @JsonProperty("sendTimestamp")
    private Long sendTimestamp;
    @JsonProperty("messageType")
    private Integer messageType;
    @JsonProperty("payload")
    private MessagePayload payload;
    @JsonProperty("externalRequestId")
    private String externalRequestId;

    /**
     * 发送消息是否成功
     * @return 发送成功
     */
    public boolean success() {
        return 0 == this.getErrorCode();
    }

    /**
     * 是否已被删除
     * @return 拉黑、删除等情况均视为删除
     */
    public boolean deleted() {
        return ArrayUtil.contains(DELETED_STATUS, this.getErrorCode());
    }

//  errorCode
//0: (发送成功) msg 为空
//
//101: 系统错误，请重启后稍后重试
//102: 无效的url
//103: 托管微信已掉线，请启动后重试
//104: 托管微信网络异常，请检查网络或者重启后再试
//105: 群聊不存在或者加载失败，请同步已有群聊后重试
//106: 联系人不存在或者加载失败，请同步已有联系人后重试
//107: 名片发送失败，请检查该名片是否是好友关系
//108: 小程序发送失败，请检查小程序参数是否正确
//
//150: 对联系人发送消息过于频繁被限制
//151: 对群聊发送消息过于频繁被限制
//152: 该托管账号已不在群聊中
//153: 对方已不在企业中
//154: 该托管账号没有外部联系人权限
//155: 发送文件失败
//156: 发送群公告失败
//157: 对方开启了联系人验证，你还不是他的联系人
//158: 对方将您拉黑了
//159: 对方已将您删除
//160: 该托管账号相关功能被封禁
//161: 对方无外部联系人权限
//
//201: 托管微信未完成数据同步，请稍后再试
//202: 托管微信云端客户端出错，请稍后重试
//203: 超过账号同时发送消息数量限制，请稍后重试
//204: 托管微信发送消息失败，请重试，如多次失败请在秒回中重启后再试
//205: 客户端崩溃尚未启动完成，请稍后重试
//
//1001: 消息发送错误，不确定消息是否发送成功，建议确认是否发送成功或重试，如多次超时，请联系客服
//1002: 消息发送超时，不确定消息是否发送成功，建议确认是否发送成功或重试，如多次超时，请联系客服
//1100: 未知原因
}
