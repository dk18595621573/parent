package com.cloud.component.huasheng.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.cloud.component.huasheng.consts.HSConst;
import com.cloud.component.huasheng.exception.HSException;
import com.cloud.component.properties.HSProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 签名工具类
 */
@Slf4j
public class HSUtil {

    // 华盛配置属性
    private static HSProperties hsProperties;

    public HSUtil(HSProperties hsProperties) {
        this.hsProperties = hsProperties;
    }

    /**
     * 华盛请求方法
     * @param requestMap  请求入参
     * @return
     * @throws Exception
     */
    public static <T> T doProcess(String method, Map<String, Object> requestMap, Class respClass) {
        try {
            // 获取公共参数
            Map<String, Object> map = getRequestMap(method, DateUtil.format(DateUtil.date(), DatePattern.PURE_DATETIME_FORMAT));
            // 参数赋值
            map.putAll(requestMap);
            log.info("华盛接口调用参数：{}", map);

            // 发送请求
            HttpRequest request = HttpRequest.post(getUrl(map)).body(JSONObject.toJSONString(map)).timeout(5000);
            request.removeHeader(Header.USER_AGENT);
            String response = request.execute().body();
            log.info("华盛返回结果：{}", response);

            // 封装返回参数
            Assert.notBlank(response, "返回结果为空");
            return (T) JSONUtil.toBean(response, respClass);
        } catch (Exception e) {
            log.info("华盛接口返回失败:{}", e.getMessage());
            throw new HSException( "华盛接口失败："+ e.getMessage());
        }
    }

    /**
     * 将公共参数拼接到URL
     * @param param
     * @return
     */
    public static String getUrl(Map<String, Object> param){
        return new StringBuilder(hsProperties.getUrl()).append("?").append("method=").append(param.get("method")).append("&")
                .append("timestamp=").append(param.get("timestamp")).append("&")
                .append("app_key=").append(param.get("app_key")).append("&")
                .append("v=").append(param.get("v")).append("&")
                .append("format=").append(param.get("format")).append("&")
                .append("sign_method=").append(param.get("sign_method")).append("&")
                .append("sign=").append(param.get("sign")).toString();

    }

    /**
     * 获取公共参数
     * @param method
     * @param method
     * @return
     */
    public static Map<String, Object> getRequestMap(String method, String timestamp){
        // 公共参数
        Map<String, Object> param = new HashMap<>();
        param.put("method", method);
        param.put("timestamp", timestamp);
        param.put("app_key", hsProperties.getAppkey());
        param.put("v", hsProperties.getVersion());
        param.put("sign_method", HSConst.SIGN_METHOD);
        param.put("format", HSConst.FORMAT);
        // 对公共参数签名
        String signStr = digest(param, hsProperties.getSecret());
        log.info("华盛接口公共参数签名：{}", signStr);
        param.put("sign", signStr);
        return param;
    }

    /**
     * 以Map中key的字符顺序排序后签名，如果secretKey不为空，排在最后面签名。<br/>
     * 比如：Map中值如下：<br/>
     * keyA=valueA<br/>
     * keyB=valueB<br/>
     * keyA1=valueA1<br/>
     * <br/>
     * security_check_code为yjf<br/>
     * <p>
     * 待签名字符串为：<br/>
     * keyA=valueA&keyA1=valueA1&keyB=valueByjf<br/>
     * <b>注意:</b>SIGN_KEY不会被签名
     *
     * @param dataMap
     * @param secret 密钥
     * @return
     */
    private static <T> String digest(Map<String, T> dataMap, String secret) {
        if (dataMap == null) {
            throw new IllegalArgumentException("数据不能为空");
        }

        TreeMap<String, T> treeMap = new TreeMap<String, T>(dataMap);
        StringBuilder sb = new StringBuilder(secret);
        for (Entry<String, T> entry : treeMap.entrySet()) {
            log.debug(entry.getKey() + ":" + entry.getValue() + " ; ");
            if (entry.getValue() == null) {
                throw new IllegalArgumentException("待签名值不能为空");
            }
            if (entry.getKey().equals(HSConst.SIGN_KEY)) {
                continue;
            }
            sb.append(entry.getKey()).append(entry.getValue().toString()).append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(secret);

        byte[] toDigest;
        try {
            String str = sb.toString();
            toDigest = str.getBytes(HSConst.UTF8);
            log.debug("待签名url:" + str);
            MessageDigest md = MessageDigest.getInstance(HSConst.SIGN_METHOD);
            md.update(toDigest);
            return new String(Hex.encodeHex(md.digest()));
        } catch (Exception e) {
            throw new RuntimeException("签名失败", e);
        }
    }


}
