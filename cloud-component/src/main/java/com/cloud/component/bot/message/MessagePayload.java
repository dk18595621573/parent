package com.cloud.component.bot.message;

import com.cloud.component.bot.consts.MessageType;
import lombok.Data;

import java.io.Serializable;

/**
 * 消息体.
 *
 * @author zenghao
 * @date 2022/8/30
 */
@Data
public class MessagePayload implements Serializable {

    /**
     * 文本消息内容
     */
    private String text;

    /**
     * 图片消息链接
     */
    private String url;

    /**
     * 链接地址
     */
    private String sourceUrl;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String summary;

    /**
     * 封面图片地址
     */
    private String imageUrl;

    public Message toMessage(MessageType type) {
        switch (type) {
            case TEXT:
                return new TextMessage(getText());
            case IMAGE:
                return new ImageMessage(getUrl());
            case URL_LINK:
                return new LinkMessage(getSourceUrl(), getTitle(), getSummary(), getImageUrl());
        }
        return null;
    }
}
