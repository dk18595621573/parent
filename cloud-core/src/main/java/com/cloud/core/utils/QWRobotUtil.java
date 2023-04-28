package com.cloud.core.utils;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class QWRobotUtil {

    /**
     * 企微机器人文件上传URL
     */
    private static final String MEDIA_UPLOAD_URL = "https://qyapi.weixin.qq.com/cgi-bin/webhook/upload_media?key=%s&type=file";
    /**
     * 企微机器人发送消息URL
     */
    private static final String SEND_MESSAGE_URL = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=%s";

    /**
     * 上传文件最大大小，单位字节
     */
    private static final long MAX_FILE_SIZE = 15L * 1024 * 1024;

    /**
     * 艾特用户.
     *
     * @param user 用户
     * @return true 发送成功 ; false 发送失败
     */
    public static boolean at(final String secret, final String user) {
        String json = "{\"msgtype\": \"text\",\"text\": {\"mentioned_mobile_list\":[\"" + user + "\"]}}";
        return sendMsg(secret, json);
    }

    /**
     * 发送企微机器人文本消息
     *
     * @param content 文本消息内容
     * @return true 发送成功 ; false 发送失败
     */
    public static boolean sendTextMsg(final String secret, final String content) {
        String json = "{\"msgtype\": \"text\",\"text\": {\"content\": \"" + content + "\"}}";
        return sendMsg(secret, json);
    }

    /**
     * 发送企微机器人文本消息
     *
     * @param secret 机器人密钥
     * @param content 文本消息内容
     * @param atUser 需要 @ 的用户的手机号
     * @return true 发送成功 ; false 发送失败
     */
    public static boolean sendTextMsg(final String secret, final String content, final String atUser) {
        String json = "{\"msgtype\": \"text\",\"text\": {\"content\": \"" + content + "\", \"mentioned_mobile_list\":[\"" + atUser + "\"]}}";
        return sendMsg(secret, json);
    }

    /**
     * 发送企微机器人markdown格式消息
     *
     * @param content markdown格式消息
     * @return true 发送成功 ; false 发送失败
     */
    public static boolean sendMarkdownMsg(final String secret, final String content) {
        String json = "{\"msgtype\": \"markdown\",\"markdown\": {\"content\": \"" + content + "\"}}";
        return sendMsg(secret, json);
    }

    /**
     * 发送企微机器人文件消息（包括图片、音频、视频、普通文件）
     *
     * @param secret key
     * @param file file
     * @return true 发送成功 ; false 发送失败
     */
    public static boolean sendFileMsg(final String secret, final File file) {
        if (!file.exists() || file.isDirectory() || file.length() > MAX_FILE_SIZE) {
            // 文件不存在或者超过最大大小
            return false;
        }
        // 上传素材
        String responseStr = HttpUtil.createPost(String.format(MEDIA_UPLOAD_URL, secret))
                .contentType(ContentType.MULTIPART.toString())
                .form("media", file)
                .execute()
                .body();
        JSONObject responseMap = JSONUtil.parseObj(responseStr);
        if (!responseMap.containsKey("media_id")) {
            // 素材上传失败
            return false;
        }
        // 发送文件消息
        String json = "{\"msgtype\": \"file\",\"file\": {\"media_id\": \"" + responseMap.getStr("media_id") + "\"}}";
        return sendMsg(secret, json);
    }

    /**
     * 发送消息
     * @param secret 企微群机器人密钥
     * @param requestBody 请求体
     * @return 请求结果
     */
    public static boolean sendMsg(final String secret, final String requestBody) {
        HttpRequest httpRequest = HttpUtil.createPost(String.format(SEND_MESSAGE_URL, secret)).body(requestBody).timeout(5000).contentType(ContentType.JSON.toString());
        try (HttpResponse response = httpRequest.execute()){
            return response.getStatus() == HttpStatus.HTTP_OK;
        } catch (Exception e) {
            log.error("企微群机器人消息发送失败[{}]:{} ", secret, requestBody, e);
        }
        return false;
    }

}