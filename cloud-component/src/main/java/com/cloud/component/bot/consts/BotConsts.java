package com.cloud.component.bot.consts;

/**
 * 企微机器人常量.
 *
 * @author zenghao
 * @date 2022/8/29
 */
public interface BotConsts {

    int WXWORK_API = 1;

    int BOT_API = 2;

    /**
     * 请求太频繁
     */
    int HTTP_STATUS_FREQUENT = 429;

    /**
     * 错误码：请求太频繁
     */
    int ERROR_CODE_FREQUENT = -100;
}
