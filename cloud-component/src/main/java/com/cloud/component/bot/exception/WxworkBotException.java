package com.cloud.component.bot.exception;

/**
 * 企微机器人 异常.
 *
 * @author zenghao
 * @date 2022/8/29
 */
public class WxworkBotException extends RuntimeException {

    private Integer code;

    public WxworkBotException(final String message) {
        super(message);
    }

    public WxworkBotException(final Integer code, final String message) {
        super(message);
        this.code = code;
    }

    public WxworkBotException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public Integer getCode() {
        return code;
    }
}
