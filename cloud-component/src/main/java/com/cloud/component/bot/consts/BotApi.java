package com.cloud.component.bot.consts;

import cn.hutool.http.Method;

/**
 * 企微机器人枚举.
 *
 * @author zenghao
 * @date 2022/8/29
 */
public interface BotApi {

    /**
     * 接口类型 1.企微api接口 2.机器人接口
     */
    Integer getType();

    /**
     * 接口请求方式
     */
    Method getMethod();

    /**
     * 接口地址
     */
    String getUrl();

    Class<?> getResponseClass();

    default boolean isBotRequest() {
        return getType() == BotConsts.BOT_API;
    }

    /**
     * 接口名称标识.
     * @return
     */
    String name();
}
