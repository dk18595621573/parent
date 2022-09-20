package com.cloud.component.bot;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.cloud.common.utils.json.JsonUtil;
import com.cloud.component.bot.consts.BotApiEnums;
import com.cloud.component.bot.consts.BotConsts;
import com.cloud.component.bot.consts.MessageType;
import com.cloud.component.bot.exception.WxworkBotException;
import com.cloud.component.bot.message.LinkMessage;
import com.cloud.component.bot.message.TextMessage;
import com.cloud.component.bot.request.BotEvent;
import com.cloud.component.bot.request.ConsumerInfo;
import com.cloud.component.bot.request.ConsumerMessage;
import com.cloud.component.bot.request.ContactRequest;
import com.cloud.component.bot.request.ContactWayAddRequest;
import com.cloud.component.bot.request.SyncConsumerInfo;
import com.cloud.component.bot.response.ApiResponse;
import com.cloud.component.bot.response.BotResponse;
import com.cloud.component.bot.response.BotUser;
import com.cloud.component.bot.response.Contact;
import com.cloud.component.bot.response.ContactWayAddResponse;
import com.cloud.component.bot.response.ContactWayResponse;
import com.cloud.component.properties.WxworkBotProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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

    /**
     * 添加渠道二维码
     * @param user 渠道二维码绑定的用户id
     * @param state 渠道二维码参数
     * @return 渠道二维码响应数据
     */
    public ContactWayAddResponse addContactWay(final String user, final String state) {
        ContactWayAddRequest request = new ContactWayAddRequest();
        request.setType(ContactWayAddRequest.TYPE_SINGLE);
        request.setScene(ContactWayAddRequest.SCENE_QRCODE);
        request.setState(state);
        request.setUser(ListUtil.toList(user));
        return exceute(BotApiEnums.CONTACTWAY_ADD, request.toMap());
    }

    /**
     * 查询渠道二维码信息
     * @param configId 渠道二维码configId
     * @return 渠道二维码信息
     */
    public ContactWayResponse.ContactWay queryContactWay(final String configId) {
        Map<String, Object> map = new HashMap<>();
        map.put("config_id", configId);
        ContactWayResponse response = exceute(BotApiEnums.CONTACTWAY_GET, map);
        return response.getContactWay();
    }

    /**
     * 获取当前托管的机器人列表
     * @return 托管的机器人列表
     */
    public List<BotUser> bots() {
        BotResponse response = exceute(BotApiEnums.BOT_LIST, CollUtil.newHashMap());
        return JsonUtil.parseList(JsonUtil.toJson(response.getData()), BotUser.class);
    }

    /**
     * 获取托管机器人的联系人列表
     * @param request 请求参数
     * @return 联系人信息
     */
    public List<Contact> contactList(final ContactRequest request) {
        BotResponse response = exceute(BotApiEnums.CONCAT_LIST, request.toMap());
        return JsonUtil.parseList(JsonUtil.toJson(response.getData()), Contact.class);
    }

    /**
     * 发送文本消息
     * @param chatId 会话id
     * @param text 文本消息内容
     */
    public BotResponse sendText(final String chatId, final String text) {
        Map<String, Object> param = new HashMap<>();
        param.put("messageType", MessageType.TEXT.getCode());
        param.put("chatId", chatId);
        param.put("payload", new TextMessage(text));
        return exceute(BotApiEnums.MESSAGE_SEND, param);
    }

    /**
     * 发送文本消息
     * @param chatId 会话id
     * @param title 链接标题
     * @param image 图片地址
     * @param summary 链接描述
     * @param link 链接地址
     */
    public BotResponse sendLink(final String chatId, final String title, final String image, final String summary, final String link) {
        Map<String, Object> param = new HashMap<>();
        param.put("messageType", MessageType.URL_LINK.getCode());
        param.put("chatId", chatId);
        param.put("payload", new LinkMessage(link, title, summary, image));
        return exceute(BotApiEnums.MESSAGE_SEND, param);
    }

    /**
     * 通知回调响应成功
     * @return 通知回调响应成功
     */
    public String notifySuccess() {
        return "{\"errCode\":0}";
    }

    public ConsumerMessage.MessageInfo parseConsumerMessage(final String data) {
        log.info("[BOT]收到好友发来信息:{}", data);
        ConsumerMessage consumerMessage = JsonUtil.parse(data, ConsumerMessage.class);
        return Optional.ofNullable(consumerMessage).map(ConsumerMessage::getData).orElse(null);
    }

    /**
     * 新增客户回调数据解析
     * @param sign 签名数据
     * @param data 回调数据
     * @return 解析的客户数据
     */
    public ConsumerInfo parseConsumerInfo(final String sign, final String data) {
        log.info("[BOT]收到添加好友信息:【{}】【{}】", sign, data);
        BotEvent<ConsumerInfo> botEvent = JsonUtil.parseGeneric(data, BotEvent.class, ConsumerInfo.class);
        return botEvent.getData();
    }

    /**
     * 数据同步回调解析
     * @param sign 签名数据
     * @param data 回调数据
     * @return 解析后的数据
     */
    public SyncConsumerInfo parseSyncInfo(final String sign, final String data) {
        log.info("[BOT]收到数据同步:【{}】【{}】", sign, data);
        return JsonUtil.parse(data, SyncConsumerInfo.class);
    }

    public <T> T exceute(final BotApiEnums botApi, final Map<String, Object> param) {
        String url;
        Map<String, Object> requestMap = Objects.isNull(param) ? new HashMap<>() : param;
        boolean isBotRequest = botApi.isBotRequest();
        if (isBotRequest) {
            url = properties.getBotDomain() + botApi.getUrl();
            requestMap.put("token", properties.getBotToken());
        } else {
            url = properties.getApiDomain() + botApi.getUrl() + "?token=" + properties.getApiToken();
        }
        String result = exceute(url, botApi.getMethod(), requestMap);
        if (isBotRequest) {
            BotResponse response = JsonUtil.parse(result, BotResponse.class);
            if (Objects.isNull(response)) {
                log.warn("机器人接口响应数据转换失败[{}]:{}", botApi.name(), result);
                return null;
            }
            if (response.getCode() != BotResponse.SUCCESS_CODE) {
                throw new WxworkBotException(response.getMessage());
            }
            return (T) response;
        } else {
            Object object = JsonUtil.parse(result, botApi.getResponseClass());
            if (Objects.isNull(object) || !(object instanceof ApiResponse)) {
                log.warn("句子API响应数据转换失败[{}]:{}", botApi.name(), result);
                return null;
            }
            ApiResponse response = (ApiResponse) object;
            if (response.getErrcode() != ApiResponse.SUCCESS_CODE) {
                throw new WxworkBotException(response.getErrmsg());
            }
            return (T) object;
        }
    }

    public String exceute(final String url, final Method method, final Map<String, Object> param) {
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
        log.info("[BOT]发起请求【{}】:{}", url, param);
        HttpResponse execute = request.setConnectionTimeout(5000).execute();
        if (execute.getStatus() == BotConsts.HTTP_STATUS_FREQUENT) {
            log.error("[BOT]响应[请求太频繁，稍后再试]:{}", url);
            throw new WxworkBotException(BotConsts.ERROR_CODE_FREQUENT, "请求太频繁，稍后再试");
        }
        String body = execute.body();
        log.info("[BOT]响应结果【{}】:{}", url, body);
        return body;
    }

}
