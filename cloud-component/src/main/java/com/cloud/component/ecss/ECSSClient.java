package com.cloud.component.ecss;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpUtil;
import com.cloud.common.utils.sign.Md5Utils;
import com.cloud.component.ecss.bean.request.BaseRequest;
import com.cloud.component.ecss.bean.request.order.DeliverGoodsRequest;
import com.cloud.component.ecss.bean.request.order.OrderCreateRequest;
import com.cloud.component.ecss.bean.request.order.OrderInquiryRequest;
import com.cloud.component.ecss.bean.response.ECSSResponse;
import com.cloud.component.ecss.bean.response.order.DeliverGoodsResponse;
import com.cloud.component.ecss.bean.response.order.OrderCreateResponse;
import com.cloud.component.ecss.bean.response.order.OrderInquiryResponse;
import com.cloud.component.ecss.config.EcssConfigHolder;
import com.cloud.component.ecss.consts.ECSSConst;
import com.cloud.component.ecss.exception.ECSSApiException;
import com.cloud.component.ecss.exception.ECSSRuntimeException;
import com.cloud.component.ecss.utils.XmlUtil;
import com.cloud.component.properties.ECSSProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * 广移平台客户端.
 *
 * @author Luo
 * @date 2023-3-17 13:19:33
 */
@Slf4j
@AllArgsConstructor
public class ECSSClient {

    /**
     * 超时时间.
     */
    private static final int SOCKET_TIMEOUT = 45000;

    /**
     * 连接超时时间.
     */
    private static final int CONNECT_TIMEOUT = 45000;

    private final ECSSProperties ecssProperties;

    /**
     * 切换平台客户端.
     *
     * @param name 配置名称
     * @return 客户端
     */
    public ECSSClient switchoverTo(String name) {
        if (ecssProperties.getConfigs().containsKey(name)) {
            EcssConfigHolder.set(name);
            return this;
        }
        log.error("ECSS平台配置【{}】不存在", name);
        throw new ECSSRuntimeException("ECSS平台配置不存在");
    }

    /**
     * 订单同步接口.
     *
     * @param request 请求参数
     * @return 结果
     */
    public OrderCreateResponse orderCreate(final OrderCreateRequest request) throws ECSSApiException {
        Assert.notNull(request, "请求参数不能为空");
        log.info("[ECSS平台] - 订单同步接口请求参数：{}", request.getXmlParams());
        // 执行请求调度
        return this.executeInternal(request, true);
    }

    /**
     * 订单查询（获取）接口.
     *
     * @param request 请求参数
     * @return 结果
     */
    public OrderInquiryResponse orderInquiry(final OrderInquiryRequest request) throws ECSSApiException {
        Assert.notNull(request, "请求参数不能为空");
        log.info("[ECSS平台] - 订单查询（获取）接口请求参数：{}", request.getJsonParams());
        // 执行请求调度
        return this.executeInternal(request, false);
    }

    /**
     * 发货接口.
     *
     * @param request 请求参数
     * @return 结果
     */
    public DeliverGoodsResponse deliverGoods(final DeliverGoodsRequest request) throws ECSSApiException {
        Assert.notNull(request, "请求参数不能为空");
        log.info("[ECSS平台] - 发货接口请求参数：{}", request.getXmlParams());
        // 执行请求调度
        return this.executeInternal(request, true);
    }

    /**
     * 签名校验.
     *
     * @param sign 需要校验的签名
     * @param map  签名数据
     * @return 结果
     */
    public boolean verifySign(final String sign, final Map<String, Object> map) {
        // 获取签名
        String verifySign = this.getSign(map);
        return sign.equals(verifySign);
    }

    /**
     * 执行请求调用.
     *
     * @param request 请求
     * @param isXml   是否xml参数
     * @param <T>     具体的API响应类
     * @return 具体的API响应结果
     * @throws ECSSApiException API调用异常
     */
    private <T extends ECSSResponse> T executeInternal(final BaseRequest<T> request, final boolean isXml) throws ECSSApiException {
        // 获取配置
        ECSSProperties.EcssConfig ecssConfig = getEcssConfig(EcssConfigHolder.get());
        // 接口系统请求参数
        HashMap<String, Object> hashMap = MapUtil.newHashMap();
        hashMap.put(ECSSConst.APP_KEY_KEY, ecssConfig.getAppKey());
        hashMap.put(ECSSConst.SESSION_KEY, ecssConfig.getSession());
        hashMap.put(ECSSConst.METHOD_KEY, request.getMethod());
        hashMap.put(ECSSConst.SIGN_METHOD_KEY, ECSSConst.MD5);
        String now = DateUtil.now();
        hashMap.put(ECSSConst.TIMESTAMP_KEY, now);
        // 生成签名
        hashMap.put(ECSSConst.SIGN_KEY, getSign(hashMap));
        // 不参与签名的参数
        hashMap.put(ECSSConst.SHOP_ID_KEY, ecssConfig.getShopId());
        hashMap.put(ECSSConst.APP_SECRET_KEY, ecssConfig.getAppSecret());
        // 应用参数传递方式
        if (isXml) {
            hashMap.put(ECSSConst.XML_KEY, request.getXmlParams());
        } else {
            hashMap.putAll(request.getParams());
        }
        // 请求地址
        String url = ecssProperties.getUrl();
        log.info("【请求地址】：{}", url);
        // 发送请求
        String response;
        ECSSResponse ecssResponse;
        try {
            response = HttpUtil.createPost(url)
                .setReadTimeout(SOCKET_TIMEOUT)
                .setConnectionTimeout(CONNECT_TIMEOUT).form(hashMap).charset(CharsetUtil.UTF_8)
                .contentType(ContentType.FORM_URLENCODED.getValue()).execute().body();
            log.info("【响应参数】：{}", response);
            // 数据转换
            ecssResponse = XmlUtil.toBean(response, ECSSResponse.class);
        } catch (Exception e) {
            log.error("调用ECSS平台接口异常：{}", ExceptionUtils.getStackTrace(e));
            throw new ECSSApiException("调用ECSS平台接口异常");
        }
        if (Objects.isNull(ecssResponse)) {
            throw new ECSSApiException("调用ECSS平台接口失败");
        }
        // 判断是否请求成功
        if (!ecssResponse.success()) {
            throw new ECSSApiException(ecssResponse.getCode(), ecssResponse.getDesc());
        }
        return XmlUtil.toBean(response, request.getResponseClass());
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
        // 头和尾需要拼接appSecret
        String appSecret = MapUtil.getStr(map, ECSSConst.APP_SECRET_KEY);
        // 获取配置
        appSecret = StringUtils.isBlank(appSecret) ? getEcssConfig(EcssConfigHolder.get()).getAppSecret() : appSecret;
        // key + value ...... key + value
        StringBuilder builder = new StringBuilder(appSecret);
        for (Map.Entry<String, Object> entry : treeMap.entrySet()) {
            // 跳过生成签名的数据
            if (ECSSConst.IGNORE_KEY.contains(entry.getKey())) {
                continue;
            }
            builder.append(entry.getKey()).append(entry.getValue());
        }
        builder.append(appSecret);
        log.info("签名数据：{}", builder);
        String sign = Md5Utils.hash(builder.toString()).toUpperCase();
        log.info("签名：{}", sign);
        return sign;
    }

    /**
     * 获取当前需要平台配置.
     *
     * @param configName 配置名称
     * @return ECSS平台配置
     */
    private ECSSProperties.EcssConfig getEcssConfig(String configName) {
        checkEcssConfig();
        ECSSProperties.EcssConfig config = ecssProperties.getConfigs().get(configName);
        if (Objects.isNull(config)) {
            log.error("没有找到【{}】相关平台配置：{}", configName, ecssProperties);
            throw new ECSSRuntimeException("ECSS平台配置错误");
        }
        return config;
    }

    /**
     * 校验配置.
     */
    private void checkEcssConfig() {
        if (MapUtil.isEmpty(ecssProperties.getConfigs())) {
            log.error("没有找到ECSS平台配置：{}", ecssProperties);
            throw new ECSSRuntimeException("ECSS平台配置错误");
        }
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
