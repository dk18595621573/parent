package com.cloud.component.cmb.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cloud.component.cmb.exception.CMBException;
import com.cloud.component.cmb.request.BaseRequest;
import com.cloud.component.cmb.request.CMBRequest;
import com.cloud.component.cmb.response.BaseResponse;
import com.cloud.component.properties.CMBProperties;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * 招商工具类
 */
@Slf4j
public class CMBUtils {

    /**
     * 超时时间 .
     */
    private static final int SOCKET_TIMEOUT = 60000;
    /**
     * 连接超时时间 .
     */
    private static final int CONNECT_TIMEOUT = 15000;

    /**
     * 响应成功标识码
     */
    private static final String SUCCESS_CODE = "SUC0000";

    /**
     * 获取时间戳的方法，用来生成唯一的银行接口参数reqid
     * @return
     */
    public static String getTimestamp() {
        Random random = new Random();
        int num = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
        String rand = DateUtil.format(DateUtil.date(), DatePattern.PURE_DATETIME_MS_PATTERN) + num;
        return rand;
    }

    /**
     * 发送http请求到前置机
     * @param httpUrl
     * @param param
     * @return
     */
    public static String doPostForm(String httpUrl, Map param) {
        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        String result = null;
        try {
            URL url = new URL(httpUrl);
            SSLContext sslcontext;
            sslcontext = SSLContext.getInstance("SSL", "SunJSSE");
            sslcontext.init(null, new TrustManager[] { new MyX509TrustManager() }, new java.security.SecureRandom());
            HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
                public boolean verify(String s, SSLSession sslsession) {
                    log.warn("主机名与证书不匹配");
                    return true;
                }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(CONNECT_TIMEOUT);
            connection.setReadTimeout(SOCKET_TIMEOUT);
            connection.setInstanceFollowRedirects(true);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            os = connection.getOutputStream();
            os.write(createLinkString(param).getBytes());
            if (connection.getResponseCode() != 200) {
                is = connection.getErrorStream();
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            } else {
                is = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            throw new CMBException(e.getMessage());
        } catch (IOException e) {
            throw new CMBException(e.getMessage());
        } catch (Exception e) {
            throw new CMBException(e.getMessage());
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
     * 创建链接字符串
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String createLinkString(Map<String, String> params) throws UnsupportedEncodingException {
        ArrayList<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        StringBuilder prestr = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {
                prestr.append(key).append("=").append(value);
            } else {
                prestr.append(key).append("=").append(value).append("&");
            }
        }
        return prestr.toString();
    }


    /**
     * 获取请求参数
     * @param baseRequest  请求参数
     * @return
     */
    public static <T> String getRequestString(BaseRequest<T> baseRequest, CMBProperties cmbProperties) {
        String funcode = baseRequest.getFuncode();
        Assert.notBlank(funcode, "funcode不能为空");
        T body = baseRequest.getBody();
        Assert.notNull(body, "body不能为空");

        CMBRequest<T> cmbRequest = new CMBRequest<>();
        cmbRequest.setBody(body);

        CMBRequest.HeadRequest headRequest = new CMBRequest.HeadRequest();
        headRequest.setFuncode(funcode);
        headRequest.setReqid(CMBUtils.getTimestamp());
        headRequest.setUserid(cmbProperties.getUID());
        cmbRequest.setHead(headRequest);

        HashMap<String, CMBRequest> requestHashMap = new HashMap<>();
        requestHashMap.put("request", cmbRequest);
        return JSONUtil.toJsonStr(requestHashMap);
    }

    /**
     * 获取响应结果
     * @param response
     * @param respClass
     * @param <T>
     * @return
     */
    public static <T extends BaseResponse> T getResponse(String response, Class<T> respClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 返回结果中包含这个两个字符，可能是没有白名单，也可能用户没有权限，或者其他错误
        if ((response.contains("CDCServer:") && response.contains("ErrMsg:"))) {
            throw new CMBException(response);
        }

        T baseResponse = respClass.getDeclaredConstructor().newInstance();
        baseResponse.setSuccess(Boolean.FALSE);

        JSONObject jsonObject = JSONUtil.parseObj(response);
        JSONObject resp = jsonObject.getJSONObject("response");
        JSONObject head = resp.getJSONObject("head");
        Assert.notNull(head, "返回结果的head为空");
        String resultcode = head.getStr("resultcode");
        Assert.notBlank(resultcode, "返回结果的resultcode为空");
        // 成功
        if (SUCCESS_CODE.equals(resultcode)){
            JSONObject body = resp.getJSONObject("body");
            Assert.notNull(body, "返回结果的body为空");
            baseResponse = JSONUtil.toBean(body, respClass);
            baseResponse.setSuccess(Boolean.TRUE);
        }
        return baseResponse;
    }
}
