package com.cloud.component.cmb.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

/**
 * 招商银行工具类.
 *
 * @author nlsm
 * @date 2023-3-6 14:06:10
 */
@Slf4j
@UtilityClass
public class CmbUtils {

    /**
     * 获取时间戳的方法，用来生成唯一的银行接口参数reqid.
     *
     * @return 时间戳
     */
    public static String getTimestamp() {
        // 5位随机数
        String randomNum = RandomUtil.randomNumbers(5);
        return DateUtil.format(DateUtil.date(), DatePattern.PURE_DATETIME_MS_PATTERN) + randomNum;
    }

    /**
     * 排序.
     *
     * @param json 数据
     * @return 结果
     */
    public static String serialJsonOrdered(JsonObject json) {
        StringBuilder appender = new StringBuilder();
        appender.append("{");
        Iterator<String> keys = new TreeSet<>(json.keySet()).iterator();
        boolean isFirstEle = true;
        while (keys.hasNext()) {
            if (!isFirstEle) {
                appender.append(",");
            }
            String key = keys.next();
            Object val = json.get(key);
            if (val instanceof JsonObject) {
                appender.append("\"").append(key).append("\":");
                appender.append(serialJsonOrdered((JsonObject) val));
            } else if (val instanceof JsonArray) {
                JsonArray jarray = (JsonArray) val;
                appender.append("\"").append(key).append("\":[");
                boolean isFirstArrEle = true;
                for (int i = 0; i < jarray.size(); i++) {
                    if (!isFirstArrEle) {
                        appender.append(",");
                    }
                    Object obj = jarray.get(i);
                    if (obj instanceof JsonObject) {
                        appender.append(serialJsonOrdered((JsonObject) obj));
                    } else {
                        appender.append(obj.toString());
                    }
                    isFirstArrEle = false;
                }
                appender.append("]");
            } else {
                String value = val.toString();
                appender.append("\"").append(key).append("\":").append(value);
            }
            isFirstEle = false;
        }
        appender.append("}");
        return appender.toString();
    }

    /**
     * 发起POST请求.
     *
     * @param httpUrl 请求地址
     * @param param 参数
     * @return 结果
     * @throws Exception 异常
     */
    public static String doPostForm(String httpUrl, Map<String, String> param) throws Exception {
        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        String result = null;
        try {
            URL url = new URL(httpUrl);
            SSLContext sslcontext;
            sslcontext = SSLContext.getInstance("SSL");
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init((KeyStore) null);
            X509TrustManager defaultTm = null;
            for (TrustManager tm : tmf.getTrustManagers()) {
                if (tm instanceof X509TrustManager) {
                    defaultTm = (X509TrustManager) tm;
                    break;
                }
            }
            sslcontext.init(null, new TrustManager[]{defaultTm}, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(60000);
            connection.setInstanceFollowRedirects(true);

            connection.setDoOutput(true);
            connection.setDoInput(true);

            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            os = connection.getOutputStream();
            os.write(createLinkString(param).getBytes());
            if (connection.getResponseCode() != 200) {
                is = connection.getErrorStream();
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuilder sbf = new StringBuilder();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            } else {
                is = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuilder sbf = new StringBuilder();
                String temp = null;
                boolean firstLine = true;
                while ((temp = br.readLine()) != null) {
                    if (!firstLine) {
                        firstLine = false;
                        sbf.append("\r\n");
                    }
                    sbf.append(temp);
                }
                result = sbf.toString();
            }
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            connection.disconnect();
        }
        return result;
    }

    /**
     * 拼接参数.
     *
     * @param params 参数
     * @return 结果
     */
    private static String createLinkString(Map<String, String> params) {
        ArrayList<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {
                builder.append(key).append("=").append(value);
            } else {
                builder.append(key).append("=").append(value).append("&");
            }
        }
        return builder.toString();
    }

}
