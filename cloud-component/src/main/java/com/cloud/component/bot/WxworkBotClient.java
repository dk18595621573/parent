package com.cloud.component.bot;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.cloud.common.utils.json.JsonUtil;
import com.cloud.component.bot.consts.BotApiEnums;
import com.cloud.component.bot.consts.BotConsts;
import com.cloud.component.bot.exception.WxworkBotException;
import com.cloud.component.bot.request.BotEvent;
import com.cloud.component.bot.request.ConsumerInfo;
import com.cloud.component.bot.request.ContactRequest;
import com.cloud.component.bot.response.BaseResponse;
import com.cloud.component.bot.response.BotUser;
import com.cloud.component.bot.response.Contact;
import com.cloud.component.properties.WxworkBotProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 企业微信机器人客户端.
 *
 * @author zenghao
 * @date 2022/8/29
 */
@Slf4j
@AllArgsConstructor
public class WxworkBotClient {

    private final WxworkBotProperties properties;

    public List<BotUser> bots() {
        BaseResponse response = exceute(BotApiEnums.BOT_LIST, CollUtil.newHashMap());
        return JsonUtil.parseList(JsonUtil.toJson(response.getData()), BotUser.class);
    }

    public List<Contact> contactList(ContactRequest request) {
        BaseResponse response = exceute(BotApiEnums.CONCAT_LIST, request.toMap());
        return JsonUtil.parseList(JsonUtil.toJson(response.getData()), Contact.class);
    }

    public ConsumerInfo parseConsumerInfo(String data) {
        BotEvent<ConsumerInfo> botEvent = JsonUtil.parseGeneric(data, BotEvent.class, ConsumerInfo.class);
        return botEvent.getData();
    }

    public BaseResponse exceute(final BotApiEnums botApi, final Map<String, Object> param) {
        int type = botApi.getType();
        String url;
        if (type == BotConsts.BOT_API) {
            url = properties.getBotDomain() + botApi.getUrl();
            param.put("token", properties.getBotToken());
        } else {
            url = properties.getApiDomain() + botApi.getUrl();
            param.put("token", properties.getApiToken());
        }
        return exceute(url, botApi.getMethod(), param);
    }

    public BaseResponse exceute(final String url, final Method method, final Map<String, Object> param) {
        HttpRequest request = HttpUtil.createRequest(method, url);
        switch (method) {
            case POST:
                request.body(JsonUtil.toJson(param));
                break;
            case GET:
                request.form(param);
                break;
            default:
                request.form(param);
                break;
        }
        log.debug("请求企微机器人【{}】:{}", url, param);
        String body = request.setConnectionTimeout(5000).execute().body();
        log.debug("收到企微机器人响应【{}】:{}", url, body);
        BaseResponse response = JsonUtil.parse(body, BaseResponse.class);
        if (Objects.isNull(response)) {
            log.warn("企微机器人响应数据转换失败:{}", body);
            return null;
        }
        if (response.getCode() != BaseResponse.SUCCESS_CODE) {
            throw new WxworkBotException(response.getMessage());
        }
        return response;
    }




}
