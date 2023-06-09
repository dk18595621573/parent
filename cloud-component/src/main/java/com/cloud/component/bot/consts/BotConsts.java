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
     * 错误码：请求太频繁
     */
    int ERROR_CODE_FREQUENT = -100;

    /**
     * 错误码：无效的chatId
     */
    int ERROR_CODE_INVALID = -1;

    /**
     * 5：私聊
     */
    int PRIVATE_CHAT = 5;

    /**
     * 6：群聊
     */
    int GROUP_CHAT = 6;

}
