package com.cloud.component.bot;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.cloud.common.utils.json.JsonUtil;
import com.cloud.component.bot.consts.BotApiEnums;
import com.cloud.component.bot.consts.BotConsts;
import com.cloud.component.bot.consts.MessageType;
import com.cloud.component.bot.exception.WxworkBotException;
import com.cloud.component.bot.request.BotEvent;
import com.cloud.component.bot.request.ConsumerInfo;
import com.cloud.component.bot.request.ContactRequest;
import com.cloud.component.bot.request.SyncConsumerInfo;
import com.cloud.component.bot.response.BaseResponse;
import com.cloud.component.bot.response.BotUser;
import com.cloud.component.bot.response.Contact;
import com.cloud.component.properties.WxworkBotProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
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

    public List<Contact> contactList(final ContactRequest request) {
        BaseResponse response = exceute(BotApiEnums.CONCAT_LIST, request.toMap());
        return JsonUtil.parseList(JsonUtil.toJson(response.getData()), Contact.class);
    }

    public void sendText(final String chatId, final String text) {
        Map<String, Object> param = new HashMap<>();
        param.put("messageType", MessageType.TEXT.getCode());
        param.put("chatId", chatId);
        param.put("payload", MapUtil.builder("text", text).build());
        exceute(BotApiEnums.MESSAGE_SEND, param);
    }

    public ConsumerInfo parseConsumerInfo(final String sign, final String data) {
        log.info("[BOT]收到新好友信息:【{}】【{}】", sign, data);
        BotEvent<ConsumerInfo> botEvent = JsonUtil.parseGeneric(data, BotEvent.class, ConsumerInfo.class);
        log.info("[BOT]解析后的好友信息为:{}", botEvent);
        return botEvent.getData();
    }

    public SyncConsumerInfo parseSyncInfo(final String sign, final String data) {
        log.info("[BOT]收到数据同步:【{}】【{}】", sign, data);
        return JsonUtil.parse(data, SyncConsumerInfo.class);
    }

    public BaseResponse exceute(final BotApiEnums botApi, final Map<String, Object> param) {
        int type = botApi.getType();
        String url;
        Map<String, Object> requestMap = Objects.isNull(param) ? new HashMap<>() : param;
        if (type == BotConsts.BOT_API) {
            url = properties.getBotDomain() + botApi.getUrl();
            requestMap.put("token", properties.getBotToken());
        } else {
            url = properties.getApiDomain() + botApi.getUrl();
            requestMap.put("token", properties.getApiToken());
        }
        return exceute(url, botApi.getMethod(), requestMap);
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
        HttpResponse execute = request.setConnectionTimeout(5000).execute();
        if (execute.getStatus() == BotConsts.HTTP_STATUS_FREQUENT) {
            log.error("企微机器人响应[请求太频繁，稍后再试]:{}", url);
            return null;
        }
        String body = execute.body();
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
