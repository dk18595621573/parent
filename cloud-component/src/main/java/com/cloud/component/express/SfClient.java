package com.cloud.component.express;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cloud.common.exception.ServiceException;
import com.cloud.component.express.domain.InterceptParams;
import com.cloud.component.express.domain.InterceptResult;
import com.cloud.component.express.domain.SearchResult;
import com.cloud.component.express.util.CommonUtils;
import com.cloud.component.properties.SfProperties;
import com.cloud.component.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * 顺丰接口对接
 * @author nlsm
 */
@Slf4j
public class SfClient {

    /** 查询成功code */
    private static final String HTTP_STATUS = "200";
    /** 查询状态名称 */
    private static final String HTTP_CODE_NAME = "httpStatus";

    /** 顺丰配置文件 */
    private final SfProperties sfProperties;

    public SfClient(SfProperties sfProperties) {
        this.sfProperties = sfProperties;
    }

    /**
     * 下单结果查询
     * @param orderNumber 订单号，订单号若不填则根据运单号查询对应的订单号进行查询
     * @param mailNumber 运单号
     * @return 数据返回
     */
    public SearchResult orderSearch(String orderNumber, String mailNumber) {
        String requestId = IdUtil.randomUUID();
        String timestamp = String.valueOf(DateUtil.current(false));
        SearchResult searchResult;
        try {
            String sign = CommonUtils.generateSign(sfProperties.getAppId(), requestId, timestamp, sfProperties.getAppSecret());
            HashMap<String, String> hashMap = MapUtil.newHashMap();
            hashMap.put("appId", sfProperties.getAppId());
            hashMap.put("requestId", requestId);
            hashMap.put("timestamp", timestamp);
            hashMap.put("sign", sign);
            hashMap.put("orderNumber", orderNumber);
            hashMap.put("mailNumber", mailNumber);
            String jsonStr = JSONUtil.toJsonStr(hashMap);
            log.info("顺丰下单查询发送参数--{}", jsonStr);
            String httpPost = HttpClientUtil.doPostJson(sfProperties.getUrl() + "/api/order/search", jsonStr);
            log.info("顺丰下单返回参数--{}", httpPost);
            if (!JSONUtil.isJson(httpPost)) {
                log.info("顺丰下单查询失败--{}", httpPost);
                return new SearchResult().setOrderNumber(orderNumber).setMailNumber(mailNumber).setAbnormalReason(httpPost);
            }
            JSONObject parseObj = JSONUtil.parseObj(httpPost);
            if (!HTTP_STATUS.equals(parseObj.get(HTTP_CODE_NAME).toString())) {
                return new SearchResult().setOrderNumber(orderNumber).setMailNumber(mailNumber)
                        .setAbnormalReason(parseObj.get("message").toString());
            }
            searchResult = JSONUtil.toBean(parseObj.get("data").toString(), SearchResult.class);
        } catch (Exception e) {
            log.info("顺丰下单查询出现异常--{}", e.getMessage());
            throw new RuntimeException(e);
        }
        return searchResult;
    }

    /**
     * 运单通缉拦截接口(通用)
     * @param params 拦截入参
     */
    public InterceptResult intercept(InterceptParams params) {
        String requestId = IdUtil.randomUUID();
        String timestamp = String.valueOf(DateUtil.current(false));
        try {
            String sign = CommonUtils.generateSign(sfProperties.getAppId(), requestId, timestamp, sfProperties.getAppSecret());
            HashMap<String, String> hashMap = MapUtil.newHashMap();
            hashMap.put("appId", sfProperties.getAppId());
            hashMap.put("requestId", requestId);
            hashMap.put("timestamp", timestamp);
            hashMap.put("sign", sign);
            JSONObject parseObj = JSONUtil.parseObj(params);
            parseObj.putAll(hashMap);
            log.info("顺丰拦截发送参数--{}", parseObj);
            String httpPost = HttpClientUtil.doPostJson(sfProperties.getUrl() + "/api/waybillintercepterCommon", parseObj.toString());
            log.info("顺丰拦截返回参数--{}", httpPost);
            if (!JSONUtil.isJson(httpPost)) {
                log.info("顺丰拦截失败--{}", httpPost);
                throw new ServiceException("顺丰拦截失败");
            }
            JSONObject resultJson = JSONUtil.parseObj(httpPost);
            if (!HTTP_STATUS.equals(resultJson.get(HTTP_CODE_NAME).toString())) {
                return new InterceptResult(resultJson.get("errorCode").toString(), resultJson.get("message").toString());
            }
        } catch (Exception e) {
            log.info("顺丰拦截出现异常--{}", e.getMessage());
            throw new RuntimeException(e);
        }
        return new InterceptResult(true);
    }

}
