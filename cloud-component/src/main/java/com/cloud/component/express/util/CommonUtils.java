package com.cloud.component.express.util;

import cn.hutool.core.util.StrUtil;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 加签
 * 将 appId, requestId, timestamp, appSecret 四个字符串参数
 * 进行升序排序后再拼接得到的长字符串进行 md5（32 位）加密生成签名 sign，appSecret
 * 只参与加签过程，不参与请求。每次请求都需要带上 appId, requestId, timestamp, sign。
 *
 * @author nlsm
 */
public class CommonUtils {

    public static final Charset UTF_8 = StandardCharsets.UTF_8;
    /**
     * 十六进制
     */
    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 加签，加密
     *
     * @param appId     软件认证id
     * @param requestId 请求id
     * @param timestamp 时间戳
     * @param appSecret 软件密钥
     * @return 加密字符
     * @throws Exception
     */
    public static String generateSign(String appId, String requestId, String timestamp, String appSecret) throws Exception {
        String[] arrays = new String[]{appId, requestId, timestamp};
        if (StrUtil.isNotBlank(appSecret)) {
            arrays = new String[]{appId, requestId, timestamp, appSecret};
        }
        Arrays.sort(arrays);
        StringBuilder sb = new StringBuilder();
        for (String str : arrays) {
            sb.append(str);
        }
        try {
            return CommonUtils.encrypt32(sb.toString());
        } catch (Exception e) {
            throw new Exception("加签异常");
        }
    }

    /**
     * 对字符串进行 MD5 加密
     *
     * @param text 明文
     * @return 密文
     */
    public static String encrypt32(String text) {
        MessageDigest msgDigest;
        try {
            msgDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("System doesn't support MD5 algorithm.");
        }
        // 注意改接口是按照指定编码形式签名
        msgDigest.update(text.getBytes(UTF_8));
        byte[] bytes = msgDigest.digest();
        return new String(encodeHex(bytes));
    }

    private static char[] encodeHex(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }
        return out;
    }

}
