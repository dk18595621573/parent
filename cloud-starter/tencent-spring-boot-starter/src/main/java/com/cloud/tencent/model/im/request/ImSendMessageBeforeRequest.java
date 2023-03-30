package com.cloud.tencent.model.im.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ImSendMessageBeforeRequest implements Serializable {

    /**
     * 回调命令
     */
    @JsonProperty("CallbackCommand")
    private String callbackCommand;

    /**
     * 消息发送者 UserID
     */
    @JsonProperty("From_Account")
    private String fromAccount;

    /**
     * 消息接收者 UserID
     */
    @JsonProperty("To_Account")
    private String toAccount;

    /**
     * 消息序列号，用于标记该条消息（32位无符号整数）
     */
    @JsonProperty("MsgSeq")
    private Long msgSeq;

    /**
     * 消息随机数，用于标记该条消息（32位无符号整数）
     */
    @JsonProperty("MsgRandom")
    private Long msgRandom;

    /**
     * 消息的发送时间戳，单位为秒
     * 单聊消息优先使用 MsgTime 进行排序，同一秒发送的消息则按 MsgSeq 排序，MsgSeq 值越大消息越靠后
     */
    @JsonProperty("MsgTime")
    private Long msgTime;

    /**
     * 该条消息的唯一标识，可根据该标识进行 REST API 撤回单聊消息
     */
    @JsonProperty("MsgKey")
    private String msgKey;

    /**
     * 在线消息，为1，否则为0
     */
    @JsonProperty("OnlineOnlyFlag")
    private Integer onlineOnlyFlag;

    /**
     * 消息体，详情请参见 消息格式描述
     */
    @JsonProperty("MsgBody")
    private ImMsgBodyRequest msgBody;

    /**
     * 消息自定义数据（云端保存，会发送到对端，程序卸载重装后还能拉取到）
     */
    @JsonProperty("CloudCustomData")
    private String cloudCustomData;


}
