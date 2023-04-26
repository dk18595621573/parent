package com.cloud.component.honor;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.net.url.UrlQuery;
import cn.hutool.http.HttpUtil;
import com.cloud.common.utils.json.JsonUtil;
import com.cloud.component.honor.bean.request.BaseRequest;
import com.cloud.component.honor.bean.request.order.OrderCreateRequest;
import com.cloud.component.honor.bean.request.order.OrderQueryRequest;
import com.cloud.component.honor.bean.response.HonorResponse;
import com.cloud.component.honor.bean.response.order.OrderCreateResponse;
import com.cloud.component.honor.bean.response.order.OrderQueryResponse;
import com.cloud.component.honor.consts.HonorConst;
import com.cloud.component.honor.exception.HonorApiException;
import com.cloud.component.properties.HonorProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.nio.charset.Charset;
import java.util.Objects;

/**
 * 荣耀平台客户端.
 *
 * @author Luo
 * @date 2023-3-17 13:19:33
 */
@Slf4j
@AllArgsConstructor
public class HonorClient {

    /**
     * 超时时间.
     */
    private static final int TIMEOUT = 60000;

    private final HonorProperties honorProperties;

    /**
     * 订单创建接口.
     *
     * @param request 请求参数
     * @return 结果
     */
    public OrderCreateResponse orderCreate(final OrderCreateRequest request) throws HonorApiException {
        Assert.notNull(request, "请求参数不能为空");
        log.info("[荣耀平台] - 订单创建接口请求参数：{}", request.getJsonParams());
        // 执行请求调度
        return this.executeInternal(request);
    }

    /**
     * 订单查询接口.
     *
     * @param request 请求参数
     * @return 结果
     */
    public OrderQueryResponse orderQuery(final OrderQueryRequest request) throws HonorApiException {
        Assert.notNull(request, "请求参数不能为空");
        log.info("[荣耀平台] - 订单查询接口请求参数：{}", request.getJsonParams());
        // 执行请求调度
        return this.executeInternal(request);
    }

    /**
     * 执行请求调用.
     *
     * @param request 请求
     * @param <T>     具体的API响应类
     * @return 具体的API响应结果
     * @throws HonorApiException API调用异常
     */
    private <T extends HonorResponse> T executeInternal(final BaseRequest<T> request) throws HonorApiException {
        // 请求地址
        String path = honorProperties.getUrl().concat(request.getApiUrl());
        UrlQuery query = new UrlQuery();
        query.add(HonorConst.X_HW_ID_KEY, honorProperties.getId());
        query.add(HonorConst.X_HW_APPKEY_KEY, honorProperties.getAppKey());
        String url = UrlBuilder.of(path, Charset.defaultCharset()).setQuery(query).build();
        log.info("【请求地址】：{}", url);
        // 发送请求
        String response;
        HonorResponse honorResponse;
        try {
            response = HttpUtil.createPost(url).timeout(TIMEOUT).body(request.getJsonParams()).execute().body();
            log.info("【响应参数】：{}", response);
            honorResponse = JsonUtil.parse(response, HonorResponse.class);
        } catch (Exception e) {
            log.error("调用荣耀平台接口异常：{}", ExceptionUtils.getStackTrace(e));
            throw new HonorApiException("调用荣耀平台接口异常");
        }
        if (Objects.isNull(honorResponse)) {
            throw new HonorApiException("调用荣耀平台接口失败");
        }
        // 判断是否请求成功
        if (!honorResponse.success()) {
            throw new HonorApiException(honorResponse.getErrCode(), honorResponse.getErrMsg());
        }
        return JsonUtil.parse(response, request.getResponseClass());
    }

}
