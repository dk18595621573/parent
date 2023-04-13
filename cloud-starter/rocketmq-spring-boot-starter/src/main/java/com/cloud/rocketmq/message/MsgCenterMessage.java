package com.cloud.rocketmq.message;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.cloud.common.constant.MsgCenterConstants;
import com.cloud.rocketmq.base.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Objects;

/**
 * 消息中心消息体
 *
 * @author lifei
 * @date 2023/4/11 14:30
 * @description {@link com.cloud.common.constant.MsgCenterConstants} 消息相关常量参数
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class MsgCenterMessage extends BaseEvent {
    /**
     * 消息分类编码
     */
    private String messageTypeCode;

    /**
     * 消息接收人类型（1：公司 2：用户）
     */
    private Integer receiverType;

    /**
     * 消息接收人id
     */
    private Long receiverId;

    /**
     * 消息标题
     */
    private String messageTitle;

    /**
     * 消息内容
     */
    private String messageBody;

    /**
     * 操作反馈类型（0：无反馈 1：站内跳转 ）
     */
    private Integer feedbackType;

    /**
     * 操作反馈内容
     */
    private String feedbackContent;

    /**
     * 推送方式（1 - 站内信）
     */
    private Integer pushType;

    /**
     * 推送时间
     */
    private Date pushTime;

    /**
     * @param messageTypeCode 消息分类编码
     * @param messageBody     消息内容
     * @param receiverId      消息接收人id
     * @param feedbackType    操作反馈类型
     * @param feedbackContent 操作反馈内容
     */
    public MsgCenterMessage(String messageTypeCode, String messageTitle, String messageBody, Long receiverId, Integer feedbackType, String feedbackContent) {
        this.messageTypeCode = messageTypeCode;
        this.messageTitle = messageTitle;
        this.messageBody = messageBody;
        this.receiverType = MsgCenterConstants.ReceiverType.COMPANY.getCode();  // 默认接收人为公司
        this.receiverId = receiverId;
        this.feedbackType = Objects.isNull(feedbackType) ? MsgCenterConstants.FeedbackType.NO_FEEDBACK.getCode() : feedbackType;
        this.feedbackContent = feedbackContent;
        this.pushTime = DateUtil.date();
        this.pushType = MsgCenterConstants.PushType.IN_SITE_MAIL.getCode(); // 默认推送方式为站内信
        super.keys = IdUtil.fastSimpleUUID();
    }


    /**
     * @param messageTypeCode 消息分类编码
     * @param messageBody     消息内容
     * @param receiverId      消息接收人id
     */
    public MsgCenterMessage(String messageTypeCode, String messageTitle, String messageBody, Long receiverId) {
        this.messageTypeCode = messageTypeCode;
        this.messageTitle = messageTitle;
        this.messageBody = messageBody;
        this.receiverType = MsgCenterConstants.ReceiverType.COMPANY.getCode();  // 默认接收人为公司
        this.receiverId = receiverId;
        this.feedbackType = MsgCenterConstants.FeedbackType.NO_FEEDBACK.getCode(); // 默认为无反馈
        this.pushTime = DateUtil.date();
        this.pushType = MsgCenterConstants.PushType.IN_SITE_MAIL.getCode(); // 默认推送方式为站内信
        super.keys = IdUtil.fastSimpleUUID();
    }


}
