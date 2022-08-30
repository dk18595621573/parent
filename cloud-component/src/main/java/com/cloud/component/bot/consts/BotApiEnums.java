package com.cloud.component.bot.consts;

import cn.hutool.http.Method;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 企微机器人枚举.
 *
 * @author zenghao
 * @date 2022/8/29
 */
@Getter
@AllArgsConstructor
public enum BotApiEnums {

    BOT_LIST(BotConsts.BOT_API,  Method.GET, "/bot/list"),
    CONCAT_LIST(BotConsts.BOT_API, Method.GET, "/contact/list"),
    MESSAGE_SEND(BotConsts.BOT_API, Method.POST, "/message/send"),
    ;

    /**
     * 接口类型 1.企微api接口 2.机器人接口
     */
    private final Integer type;

    /**
     * 接口请求方式
     */
    private final Method method;

    /**
     * 接口地址
     */
    private final String url;



}
