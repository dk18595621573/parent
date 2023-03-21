package com.cloud.component.ecss;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpUtil;
import com.cloud.common.utils.sign.Md5Utils;
import com.cloud.component.ecss.bean.request.BaseRequest;
import com.cloud.component.ecss.bean.request.order.OrderCreateRequest;
import com.cloud.component.ecss.bean.response.ECSSResponse;
import com.cloud.component.ecss.bean.response.order.OrderCreateResponse;
import com.cloud.component.ecss.consts.ECSSConst;
import com.cloud.component.ecss.exception.ECSSApiException;
import com.cloud.component.properties.ECSSProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 广移平台客户端.
 *
 * @author Luo
 * @date 2023-3-17 13:19:33
 */
@Slf4j
public class ECSSClient {

    /**
     * 创建XmlMapper对象，用于实体与Json和xml之间的相互转换.
     */
    private static final XmlMapper XML_MAPPER = new XmlMapper();

    private final ECSSProperties ecssProperties;

    public ECSSClient(ECSSProperties ecssProperties) {
        this.ecssProperties = ecssProperties;
    }

    /**
     * 订单同步接口.
     *
     * @param request 请求参数
     * @return 结果
     */
    public OrderCreateResponse orderCreate(final OrderCreateRequest request) throws ECSSApiException {
        Assert.notNull(request, "请求参数不能为空");
        log.info("[ECSS平台] - 订单同步接口请求参数：{}", request.getJsonParams());
        // 执行请求调度
        return this.executeInternal(request);
    }


    /**
     * 执行请求调用.
     *
     * @param request 请求
     * @param <T>     具体的API响应类
     * @return 具体的API响应结果
     * @throws ECSSApiException API调用异常
     */
    private <T extends ECSSResponse> T executeInternal(final BaseRequest<T> request) throws ECSSApiException {
        // 接口请求参数
        HashMap<String, Object> hashMap = MapUtil.newHashMap();
        hashMap.put(ECSSConst.APP_KEY_KEY, ecssProperties.getAppKey());
        hashMap.put(ECSSConst.SESSION_KEY, ecssProperties.getSession());
        hashMap.put(ECSSConst.METHOD_KEY, request.getMethod());
        hashMap.put(ECSSConst.SIGN_METHOD_KEY, ECSSConst.MD5);
        String now = DateUtil.now();
        hashMap.put(ECSSConst.TIMESTAMP_KEY, now);
        // 生成签名
        hashMap.put(ECSSConst.SIGN_KEY, getSign(hashMap));
        // 不参与签名的参数
        hashMap.put(ECSSConst.XML_KEY, request.getXmlParams());
        hashMap.put(ECSSConst.SHOP_ID_KEY, ecssProperties.getShopId());
        hashMap.put(ECSSConst.APP_SECRET_KEY, ecssProperties.getAppSecret());
        // 请求地址
        String url = ecssProperties.getUrl();
        log.info("【请求地址】：{}", url);
        // 发送请求
        String response;
        ECSSResponse ecssResponse;
        try {
            response = HttpUtil.createPost(url).form(hashMap).charset(CharsetUtil.UTF_8).contentType(ContentType.FORM_URLENCODED.getValue()).execute().body();
            log.info("【响应参数】：{}", response);
            // 数据转换
            ecssResponse = XML_MAPPER.readValue(response, ECSSResponse.class);
        } catch (Exception e) {
            log.error("调用ECSS平台接口异常：{}", ExceptionUtils.getStackTrace(e));
            throw new ECSSApiException("调用ECSS平台接口异常");
        }
        // 判断是否请求成功
        if (!ecssResponse.success()) {
            throw new ECSSApiException(ecssResponse.getCode(), ecssResponse.getDesc());
        }
        try {
            return XML_MAPPER.readValue(response, request.getResponseClass());
        } catch (JsonProcessingException e) {
            throw new ECSSApiException("解析ECSS接口响应异常");
        }
    }

    /**
     * 获取签名.
     *
     * @param map 请求参数
     * @return 签名
     */
    private String getSign(final Map<String, Object> map) {
        // 按照字母先后顺序排序
        TreeMap<String, Object> treeMap = MapUtil.newTreeMap(map, String::compareTo);
        // key + value ...... key + value
        StringBuilder builder = new StringBuilder(ecssProperties.getAppSecret());
        for (Map.Entry<String, Object> entry : treeMap.entrySet()) {
            builder.append(entry.getKey()).append(entry.getValue());
        }
        builder.append(ecssProperties.getAppSecret());
        log.info("签名数据：{}", builder);
        String sign = Md5Utils.hash(builder.toString()).toUpperCase();
        log.info("签名：{}", sign);
        return sign;
    }


    /**
     * 根据skuid获取广移的库存
     * @param skuId
     * @return
     */
    public static Integer getSkuStock(String skuId){
        return 100;
    }



}
