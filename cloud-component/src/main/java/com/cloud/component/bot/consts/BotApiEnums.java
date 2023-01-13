package com.cloud.component.bot.consts;

import cn.hutool.http.Method;
import com.cloud.component.bot.response.BotResponse;
import com.cloud.component.bot.response.ChatResponse;
import com.cloud.component.bot.response.ContactWayAddResponse;
import com.cloud.component.bot.response.ContactWayResponse;
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
public enum BotApiEnums implements BotApi {

    BOT_LIST(BotConsts.BOT_API,  Method.GET, "/bot/list", BotResponse.class),
    CONCAT_LIST(BotConsts.BOT_API, Method.GET, "/contact/list", BotResponse.class),
    MESSAGE_SEND(BotConsts.BOT_API, Method.POST, "/message/send", BotResponse.class),
    GET_CHATID(BotConsts.WXWORK_API, Method.GET, "/api/v1/instantReply/getChatId", ChatResponse.class),
    CONTACTWAY_ADD(BotConsts.WXWORK_API, Method.POST, "/api/v1/contactWay/add", ContactWayAddResponse.class),
    CONTACTWAY_GET(BotConsts.WXWORK_API, Method.GET, "/api/v1/contactWay/get", ContactWayResponse.class),
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

    private final Class<?> responseClass;

}
