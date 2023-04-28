package com.cloud.rocketmq.message;

import cn.hutool.core.util.IdUtil;
import com.cloud.rocketmq.base.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 消息中心批量消息
 *
 * @author lifei
 * @date 2023/4/27 18:16
 * @description
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class MsgCenterBatchMessage extends BaseEvent {

    /**
     * 批量消息内容集合
     */
    private List<MsgCenterMessage> messageList;

    public MsgCenterBatchMessage(List<MsgCenterMessage> messageList) {
        this.messageList = messageList;
        super.keys = IdUtil.fastSimpleUUID();
    }
}
