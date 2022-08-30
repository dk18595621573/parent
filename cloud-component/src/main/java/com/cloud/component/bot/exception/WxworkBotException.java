package com.cloud.component.bot.exception;

/**
 * 企微机器人 异常.
 *
 * @author zenghao
 * @date 2022/8/29
 */
public class WxworkBotException extends RuntimeException {

    public WxworkBotException(final String message) {
        super(message);
    }

    public WxworkBotException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
