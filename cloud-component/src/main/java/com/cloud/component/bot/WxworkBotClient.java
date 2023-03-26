package com.cloud.component.bot;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.cloud.common.constant.HttpStatus;
import com.cloud.common.utils.StringUtils;
import com.cloud.common.utils.json.JsonUtil;
import com.cloud.component.bot.config.WxworkBotConfigHolder;
import com.cloud.component.bot.consts.BotApi;
import com.cloud.component.bot.consts.BotApiEnums;
import com.cloud.component.bot.consts.BotConsts;
import com.cloud.component.bot.consts.MessageType;
import com.cloud.component.bot.exception.WxworkBotException;
import com.cloud.component.bot.message.FileMessage;
import com.cloud.component.bot.message.ImageMessage;
import com.cloud.component.bot.message.LinkMessage;
import com.cloud.component.bot.message.Message;
import com.cloud.component.bot.message.MiniProgramMessage;
import com.cloud.component.bot.message.TextMessage;
import com.cloud.component.bot.message.VideoMessage;
import com.cloud.component.bot.request.BaseCallback;
import com.cloud.component.bot.request.BotEvent;
import com.cloud.component.bot.request.BroadcastCreateRequest;
import com.cloud.component.bot.request.ConsumerInfo;
import com.cloud.component.bot.request.ConsumerMessage;
import com.cloud.component.bot.request.ContactRequest;
import com.cloud.component.bot.request.ContactWayAddRequest;
import com.cloud.component.bot.request.MessageSendRequest;
import com.cloud.component.bot.request.SentResult;
import com.cloud.component.bot.request.SyncConsumerInfo;
import com.cloud.component.bot.response.ApiResponse;
import com.cloud.component.bot.response.BotResponse;
import com.cloud.component.bot.response.BotUser;
import com.cloud.component.bot.response.BroadcastCreateResponse;
import com.cloud.component.bot.response.ChatResponse;
import com.cloud.component.bot.response.Contact;
import com.cloud.component.bot.response.ContactWayAddResponse;
import com.cloud.component.bot.response.ContactWayResponse;
import com.cloud.component.bot.response.GroupBotResponse;
import com.cloud.component.bot.response.MessageResponse;
import com.cloud.component.bot.response.MessageSendResponse;
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

    public WxworkBotClient switchoverTo(String name) {
        if (properties.getConfigs().containsKey(name)) {
            WxworkBotConfigHolder.set(name);
            return this;
        }
        log.error("企微机器人配置【{}】不存在", name);
        throw new WxworkBotException("系统配置不存在");
    }

    /**
     * 根据机器人id获取机器的类型
     * @param botId 机器人id
     * @return botType
     */
    public String getBotType(String botId) {
        checkBotConfig();
        Optional<String> optional = properties.getConfigs()
            .entrySet().stream().filter(e -> e.getValue().getUserId().equals(botId)).map(Map.Entry::getKey).findFirst();
        return optional.orElse(null);
    }

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
     * 获取用户的会话id
     * @param unionId 用户的unionId
     * @return 会话id
     */
    public String queryChatId(final String unionId) {
        Map<String, Object> map = new HashMap<>();
        map.put("unionId", unionId);
        map.put("botUserId", getBotConfig(WxworkBotConfigHolder.get()).getUserId());
        ChatResponse response = exceute(BotApiEnums.GET_CHATID, map);
        return response.getChatId();
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
     * 获取托管账号所在微信分组的信息
     * @return 托管账号所在微信分组的信息
     */
    public GroupBotResponse groupBots() {
        return exceute(BotApiEnums.GET_GROUP_BOTS, CollUtil.newHashMap());
    }

    /**
     * 发送文本消息
     * @param chatId 会话id
     * @param text 文本消息内容
     */
    public MessageResponse sendText(final String chatId, final String text) {
        return sendMessage(chatId, MessageType.TEXT, new TextMessage(text));
    }

    /**
     * 发送图片消息
     * @param chatId 会话id
     * @param url 图片地址
     */
    public MessageResponse sendImage(final String chatId, final String url) {
        return sendMessage(chatId, MessageType.IMAGE, new ImageMessage(url));
    }

    /**
     * 发送文本消息
     * @param chatId 会话id
     * @param title 链接标题
     * @param image 图片地址
     * @param summary 链接描述
     * @param link 链接地址
     */
    public MessageResponse sendLink(final String chatId, final String title, final String image, final String summary, final String link) {
        return sendMessage(chatId, MessageType.URL_LINK, new LinkMessage(link, title, summary, image));
    }

    /**
     * 发送文件消息
     * @param chatId 会话id
     * @param url 文件地址
     */
    public MessageResponse sendFile(final String chatId, final String url) {
        String name = StringUtils.substringAfterLast(url, "/");
        return sendMessage(chatId, MessageType.FILE, new FileMessage(name, url));
    }

    /**
     * 发送小程序消息
     * @param chatId 会话id
     * @param description 描述
     * @param pagePath 跳转地址
     * @param thumbUrl 封面图地址
     */
    public MessageResponse sendMiniProgram(final String chatId, final String description, final String pagePath, final String thumbUrl) {
        WxworkBotProperties.OutMessage outMessage = properties.getMessage();
        return sendMessage(chatId, MessageType.MINI_PROGRAM,
            new MiniProgramMessage(String.format("%s@app", outMessage.getGhId()),
                description, pagePath, thumbUrl, outMessage.getTitle(), outMessage.getAppid()));
    }

    /**
     * 发送小程序消息
     * @param chatId 会话id
     * @param message 小程序消息
     */
    public MessageResponse sendMiniProgram(final String chatId, final MiniProgramMessage message) {
        return sendMessage(chatId, MessageType.MINI_PROGRAM, message);
    }

    /**
     * 发送视频消息
     * @param chatId 会话id
     * @param url 文件地址
     */
    public MessageResponse sendVideo(final String chatId, final String url) {
        return sendMessage(chatId, MessageType.VIDEO, new VideoMessage(url));
    }

    /**
     * 发送群发消息.
     *
     * @param request 请求
     * @return
     */
    public BroadcastCreateResponse sendBroadcast(final BroadcastCreateRequest request) {
        Map<String, Object> param = BeanUtil.beanToMap(request, false, true);
        BotResponse response = exceute(BotApiEnums.BROADCAST_CREATE, param);
        return JsonUtil.parse(JsonUtil.toJson(response.getData()), BroadcastCreateResponse.class);
    }

    /**
     * 企业级发送消息.
     *
     * @param request 请求
     * @return
     */
    public MessageSendResponse sendMessage(final MessageSendRequest request) {
        Map<String, Object> param = BeanUtil.beanToMap(request, false, true);
        return exceute(BotApiEnums.WXWORK_MESSAGE_SEND, param);
    }

    /**
     * 给机器人的好友发送消息
     * @param chatId 会话id
     * @param messageType 消息类型
     * @param message 消息内容
     */
    private MessageResponse sendMessage(final String chatId, final MessageType messageType, Message message) {
        Map<String, Object> param = new HashMap<>();
        param.put("messageType", messageType.getCode());
        param.put("chatId", chatId);
        param.put("payload", message);
        BotResponse response = exceute(BotApiEnums.MESSAGE_SEND, param);
        return JsonUtil.parse(JsonUtil.toJson(response.getData()), MessageResponse.class);
    }

    /**
     * 通知回调响应成功
     * @return 通知回调响应成功
     */
    public String notifySuccess() {
        return "{\"errCode\":0}";
    }

    /**
     * 用户发送消息到机器人的结果解析
     * @param data 消息体
     * @return MessageInfo
     */
    public ConsumerMessage parseConsumerMessage(final String data) {
        log.info("[BOT]收到好友发来信息:{}", data);
        BaseCallback<ConsumerMessage> callback = JsonUtil.parseGeneric(data, BaseCallback.class, ConsumerMessage.class);
        return Optional.ofNullable(callback).map(BaseCallback::getData).orElse(null);
    }

    /**
     * 给好友发送消息回调结果解析
     * @param data 回调结果
     * @return MessageInfo
     */
    public SentResult parseSentResult(final String data) {
        log.info("[BOT]给好友发信息回调:{}", data);
        BaseCallback<SentResult> callback = JsonUtil.parseGeneric(data, BaseCallback.class, SentResult.class);
        return Optional.ofNullable(callback).map(BaseCallback::getData).orElse(null);
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
        return Optional.ofNullable(botEvent).map(BotEvent::getData).orElse(null);
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

    public <T> T exceute(final BotApi botApi, final Map<String, Object> param) {
        String url;
        Map<String, Object> requestMap = Objects.isNull(param) ? new HashMap<>() : param;
        boolean isBotRequest = botApi.isBotRequest();
        if (isBotRequest) {
            url = properties.getBotDomain() + botApi.getUrl();
            requestMap.put("token", getBotConfig(WxworkBotConfigHolder.get()).getToken());
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
        if (execute.getStatus() == HttpStatus.HTTP_STATUS_FREQUENT) {
            log.error("[BOT]响应[请求太频繁，稍后再试]:{}", url);
            throw new WxworkBotException(BotConsts.ERROR_CODE_FREQUENT, "请求太频繁，稍后再试");
        }
        String body = execute.body();
        log.info("[BOT]响应结果【{}】:{}", url, body);
        return body;
    }

    /**
     * 获取当前需要使用的机器人配置.
     * @param configName 配置名称
     * @return 企微机器人配置
     */
    private WxworkBotProperties.BotConfig getBotConfig(String configName) {
        checkBotConfig();
        WxworkBotProperties.BotConfig config = properties.getConfigs().get(configName);
        if (Objects.isNull(config)) {
            log.error("没有找到【{}】相关企微机器人配置:{}", configName, properties);
            throw new WxworkBotException("系统配置错误");
        }
        return config;
    }

    private void checkBotConfig() {
        if (MapUtil.isEmpty(properties.getConfigs())) {
            log.error("没有找到企微机器人配置:{}", properties);
            throw new WxworkBotException("系统配置错误");
        }
    }
}
