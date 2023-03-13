package com.cloud.component.chinapay;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpBase;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.chinapay.secss.SecssConstants;
import com.chinapay.secss.SecssUtil;
import com.cloud.common.constant.Constants;
import com.cloud.common.utils.StringUtils;
import com.cloud.common.utils.json.JsonUtil;
import com.cloud.component.chinapay.domain.Response;
import com.cloud.component.chinapay.request.BaseRequest;
import com.cloud.component.chinapay.response.BaseResponse;
import com.cloud.component.chinapay.exception.ChinaPayException;
import com.cloud.component.chinapay.util.Base64;
import com.cloud.component.chinapay.util.Encryptor;
import com.cloud.component.properties.ChinaPayProperties;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 银联接口 工具.
 *
 * @author zenghao
 * @date 2022/6/20
 */
@Slf4j
public class ChinaPayClient {
    /**
     * 超时时间 .
     */
    private static final int SOCKET_TIMEOUT = 180000;
    /**
     * 连接超时时间 .
     */
    private static final int CONNECT_TIMEOUT = 180000;

    private final ChinaPayProperties chinaPayProperties;

    public ChinaPayClient(final ChinaPayProperties chinaPayProperties) {
        this.chinaPayProperties = chinaPayProperties;
    }


    public <T extends BaseResponse> T doExecute(BaseRequest<T> request) {
        SecssUtil secssUtil = new SecssUtil();
        secssUtil.init(this.chinaPayProperties.getMchConfig());

        request.setMerNo(chinaPayProperties.getMchNo());
        request.setOrderDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        String orderId = Constants.snowflake.nextIdStr();
        request.setOrderId(orderId);

        Map<String, Object> sensData = request.buildSensData();
        if (CollUtil.isNotEmpty(sensData)) {
            secssUtil.encryptData(JsonUtil.toJson(sensData));
            if (!SecssConstants.SUCCESS.equals(secssUtil.getErrCode())) {
                log.error("敏感信息加密发生错误，错误信息为:{}", secssUtil.getErrMsg());
                return null;
            }
            request.setSensData(secssUtil.getEncValue());
        }
        //商户签名
        Map<String, Object> reqDataMap = toRequestMap(request, secssUtil);
        String url = chinaPayProperties.getDomain() + request.requestUrl();
        log.info("银联接口[{}]请求参数[url:{}][id:{}][param:{}]", request.getBusiType(), url, orderId, reqDataMap);
        HttpResponse httpResponse = HttpUtil.createPost(url).httpVersion(HttpBase.HTTP_1_1)
            .setReadTimeout(SOCKET_TIMEOUT)
            .setConnectionTimeout(CONNECT_TIMEOUT)
            .form(reqDataMap).execute();
        String body = httpResponse.body();
        log.info("银联接口[{}]响应参数[{}]", request.getBusiType(), body);
        if (!httpResponse.isOk()) {
            log.error("银联接口响应失败[{}]:{}", httpResponse.getStatus(), body);
            throw new ChinaPayException("银联接口异常");
        }
        T result = null;
        try {
            Response response = JsonUtil.parse(JsonUtil.toJson(str2Map(body)), Response.class);
            verifySign(response.getRespData(), response.getSignature(), secssUtil);

            // 返回报文的base64 解密
            String respData = new String(Base64.decode(response.getRespData().toCharArray()), StandardCharsets.UTF_8);
            result = JsonUtil.parse(respData, request.responseClass());
            if (!BaseResponse.SUCCESS_CODE.equals(result.getRespCode()) && !BaseResponse.SUB_BANK_CODE.equals(result.getRespCode())) {
                log.error("银联接口响应失败:[{} - {}]", result.getRespCode(), result.getRespMsg());
                throw new ChinaPayException(result.getRespCode(), result.getRespMsg());
            }
        } catch (ChinaPayException e) {
            log.error("银联接口响应数据异常[{}]:{}", e.getMessage(), body);
            throw e;
        } catch (Exception e) {
            log.error("银联接口响应数据处理异常:{}", body, e);
            throw new ChinaPayException("银联响应数据处理异常");
        }
        return result;
    }

    /**
     * 响应值转换为map
     * @param respStr 响应值
     * @return map
     */
    private Map<String, String> str2Map(String respStr) {
        Map<String, String> data = new HashMap<>();
        if (StringUtils.isNotBlank(respStr)) {
            Arrays.stream(respStr.split("&"))
                .filter(StringUtils::isNotBlank)
                .forEach(str -> {
                    int index = str.indexOf("=");
                    if (index > 0) {
                        data.put(str.substring(0, index), str.substring(index + 1));
                    }
                });
        }
        return data;
    }

    /**
     * 请求参数封装成map
     * @param request 请求参数
     * @param secssUtil 安全工具类
     * @return map
     */
    private Map<String, Object> toRequestMap(BaseRequest<?> request, SecssUtil secssUtil) {
        String reqData = request.buildBase64ReqData();
        String encReqData = Encryptor.encode(reqData, Encryptor.ALG_SHA512);
        Map<String, Object> reqDataMap = new HashMap<>();
        reqDataMap.put("reqData", encReqData);
        secssUtil.sign(reqDataMap);
        String signature = secssUtil.getSign();

        reqDataMap.put("reqData", reqData);
        reqDataMap.put("instId", request.getInstId());
        reqDataMap.put("merNo", request.getMerNo());
        reqDataMap.put("signature", signature);
        return reqDataMap;
    }

    /**
     * 响应值签名校验
     * @param data 响应值
     * @param sign 签名数据
     * @param secssUtil 安全工具类
     */
    private void verifySign(String data, String sign, SecssUtil secssUtil) {
        String encRespDataStr = Encryptor.encode(data, Encryptor.ALG_SHA512);
        Map<String, String> verifyMap = new HashMap<>();
        verifyMap.put("respData", encRespDataStr);
        verifyMap.put("signature", sign);
        secssUtil.verify(verifyMap);
        if (!SecssConstants.SUCCESS.equals(secssUtil.getErrCode())) {
            throw new ChinaPayException("响应数据验签失败");
        }
    }

}
