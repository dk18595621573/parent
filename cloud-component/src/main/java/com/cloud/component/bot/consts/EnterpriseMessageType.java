package com.cloud.component.bot.consts;

import com.cloud.component.bot.message.FileMessage;
import com.cloud.component.bot.message.ImageMessage;
import com.cloud.component.bot.message.Message;
import com.cloud.component.bot.message.MiniProgramMessage;
import com.cloud.component.bot.message.TextMessage;
import com.cloud.component.bot.message.VideoMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 企业级接口 消息类型枚举.
 *
 * @author Luo
 * @date 2023-3-26 15:31:52
 */
@Getter
@AllArgsConstructor
public enum EnterpriseMessageType {

    /**
     * 1：文件.
     */
    FILE(1, FileMessage.class),

    /**
     * 2：语音.
     */
    VOICE(2, VideoMessage.class),

    /**
     * 6：图片.
     */
    IMAGE(6, ImageMessage.class),

    /**
     * 7：文字.
     */
    TEXT(7, TextMessage.class),

    /**
     * 9：小程序.
     */
    MINI_PROGRAM(9, MiniProgramMessage.class),

    /**
     * 13：视频.
     */
    VIDEO(13, VideoMessage.class),

// 以下类型仅限内测使用，需要联系客服申请内测

//    /**
//     * 5：表情.
//     */
//    EMOTICON(5, LinkMessage.class),
//
//    /**
//     * 8：位置.
//     */
//    LOCATION(8, VideoMessage.class),
//
//    /**
//     * 12：图文消息.
//     */
//    URL(12, VideoMessage.class),
//
//    /**
//     * 14：视频号.
//     */
//    CHANNEL(15, VideoMessage.class),

    ;

    /**
     * code.
     */
    private final int code;

    /**
     * 消息类型.
     */
    private final Class<? extends Message> msgClass;

    /**
     * 根据code获取.
     *
     * @param code code
     * @return 结果
     */
    public static EnterpriseMessageType getByCode(final Integer code) {
        return Arrays.stream(EnterpriseMessageType.values()).filter(i -> i.getCode() == code).findFirst().orElse(null);
    }

}