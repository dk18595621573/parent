package com.cloud.core.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.*;
import cn.hutool.json.JSONUtil;

import java.io.File;
import java.util.Map;

public class QWRobotUtil {

    // 企微机器人API URL
    private static final String API_URL = "https://qyapi.weixin.qq.com/cgi-bin/webhook/upload_media?key=%s&type=file";
    private static final String SEND_URL = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=%s";

    // 上传文件最大大小，单位字节
    private static final long MAX_FILE_SIZE = 15L * 1024 * 1024;

    /**
     * 发送企微机器人文本消息
     *
     * @param content 文本消息内容
     * @return true 发送成功 ; false 发送失败
     */
    public static boolean sendTextMsg(String secret, String content) {
        String json = "{\"msgtype\": \"text\",\"text\": {\"content\": \"" + content + "\"}}";
        HttpResponse response = HttpUtil.createPost(String.format(SEND_URL, secret)).body(json).contentType(ContentType.JSON.toString()).execute();
        return response.getStatus() == HttpStatus.HTTP_OK;
    }

    /**
     * 发送企微机器人markdown格式消息
     *
     * @param content markdown格式消息
     * @return true 发送成功 ; false 发送失败
     */
    public static boolean sendMarkdownMsg(String secret, String content) {
        String json = "{\"msgtype\": \"markdown\",\"markdown\": {\"content\": \"" + content + "\"}}";
        HttpResponse response = HttpUtil.createPost(String.format(SEND_URL, secret)).body(json).contentType(ContentType.JSON.toString()).execute();
        return response.getStatus() == HttpStatus.HTTP_OK;
    }

    /**
     * 发送企微机器人文件消息（包括图片、音频、视频、普通文件）
     *
     * @param secret key
     * @param file file
     * @return true 发送成功 ; false 发送失败
     */
    public static boolean sendFileMsg(String secret, File file) {
        if (!file.exists() || file.length() > MAX_FILE_SIZE) {
            // 文件不存在或者超过最大大小
            return false;
        }
        // 上传素材
        Map<String, Object> params = CollUtil.newHashMap();
        params.put("media", file);
        String apiUrl = String.format(API_URL, secret);
        String responseStr = HttpUtil.createRequest(Method.POST, apiUrl)
                .contentType(ContentType.MULTIPART.toString())
                .form(params)
                .execute()
                .body();
        Map<String, Object> responseMap = JSONUtil.parseObj(responseStr);
        if (!responseMap.containsKey("media_id")) {
            // 素材上传失败
            return false;
        }
        String mediaId = responseMap.get("media_id").toString();

        // 发送文件消息
        String json = "{\"msgtype\": \"file\",\"file\": {\"media_id\": \"" + mediaId + "\"}}";
        HttpResponse response = HttpUtil.createPost(String.format(SEND_URL, secret)).body(json).contentType(ContentType.JSON.toString()).execute();
        return response.getStatus() == HttpStatus.HTTP_OK;
    }

}