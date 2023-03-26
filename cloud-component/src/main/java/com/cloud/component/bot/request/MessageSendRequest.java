package com.cloud.component.bot.request;

import com.cloud.component.bot.message.Message;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 企业级消息管理接口 发送消息 请求数据.
 *
 * @author Luo
 * @date 2023-03-26 10:52
 */
@Data
@Accessors(chain = true)
public class MessageSendRequest implements Serializable {

    private static final long serialVersionUID = -5711853175759649932L;

    /**
     * 会在回调中原样带回的字段，需要保证唯一（当不唯一时报错），两月内幂等.
     * 是否必传：否
     */
    private String externalRequestId;

    /**
     * 托管账号的wxid(v1版本的botWxid), 详见imBotId.
     * 是否必传：是
     */
    private String imBotId;

    /**
     * 客户的id(v1版本的contactWxid), 详见contactWxid.
     * 是否必传：否
     */
    private String imContactId;

    /**
     * 群聊的id(v1版本的roomWxid), 详见roomWxid.
     * 是否必传：否
     */
    private String imRoomId;

    /**
     * 消息类型.
     * 是否必传：是
     */
    private Integer messageType;

    /**
     * 消息的payload.
     * 是否必传：是
     */
    private Message payload;


}

