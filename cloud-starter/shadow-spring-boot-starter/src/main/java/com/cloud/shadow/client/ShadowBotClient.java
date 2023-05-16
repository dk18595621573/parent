package com.cloud.shadow.client;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.cloud.shadow.bean.request.BaseRequest;
import com.cloud.shadow.bean.request.task.TaskQueryRequest;
import com.cloud.shadow.bean.request.task.TaskStartRequest;
import com.cloud.shadow.bean.request.task.TaskStopRequest;
import com.cloud.shadow.bean.request.token.TokenCreateRequest;
import com.cloud.shadow.bean.response.BaseResponse;
import com.cloud.shadow.bean.response.ShadowBotResponse;
import com.cloud.shadow.bean.response.task.TaskQueryResponse;
import com.cloud.shadow.bean.response.task.TaskStartResponse;
import com.cloud.shadow.bean.response.task.TaskStopResponse;
import com.cloud.shadow.bean.response.token.TokenCreateResponse;
import com.cloud.shadow.consts.ShadowBotConst;
import com.cloud.shadow.exception.ShadowBotApiException;
import com.cloud.shadow.exception.ShadowBotRuntimeException;
import com.cloud.shadow.properties.ShadowProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;

/**
 * 影刀RPA客户端.
 *
 * @author Luo
 * @date 2023-05-16 10:43
 */
@Slf4j
@AllArgsConstructor
public class ShadowBotClient {

    /**
     * 超时时间.
     */
    private static final int TIMEOUT = 60000;

    private final ShadowProperties shadowProperties;

    /**
     * 启动任务接口.
     * <p>
     * <a href="https://www.winrobot360.com/yddoc/management/5c42596f8da1e38aadbe8d426d66f18f.html">文档地址</a>
     * </p>
     *
     * @param request 请求参数
     * @return 结果
     */
    public TaskStartResponse taskStart(final TaskStartRequest request) throws ShadowBotApiException {
        Assert.notNull(request, "请求参数不能为空");
        log.info("[影刀平台] - 启动任务接口请求参数：{}", request.getJsonParams());
        // 执行请求调度
        return this.executeInternal(request);
    }

    /**
     * 查询任务运行结果接口.
     * <p>
     * <a href="https://www.winrobot360.com/yddoc/management/65cf3a312c84222a24dfe8c1d6640dcf.html">文档地址</a>
     * </p>
     *
     * @param request 请求参数
     * @return 结果
     */
    public TaskQueryResponse taskQuery(final TaskQueryRequest request) throws ShadowBotApiException {
        Assert.notNull(request, "请求参数不能为空");
        log.info("[影刀平台] - 查询任务运行结果接口请求参数：{}", request.getJsonParams());
        // 执行请求调度
        return this.executeInternal(request);
    }

    /**
     * 停止任务接口.
     * <p>
     * <a href="https://www.winrobot360.com/yddoc/management/65cf3a312c84222a24dfe8c1d6640dcf.html">文档地址</a>
     * </p>
     *
     * @param request 请求参数
     * @return 结果
     */
    public TaskStopResponse taskStop(final TaskStopRequest request) throws ShadowBotApiException {
        Assert.notNull(request, "请求参数不能为空");
        log.info("[影刀平台] - 停止任务接口请求参数：{}", request.getJsonParams());
        // 执行请求调度
        return this.executeInternal(request);
    }

    /**
     * 执行请求调用.
     *
     * @param request 请求
     * @param <T>     具体的API响应类
     * @return 具体的API响应结果
     * @throws ShadowBotApiException API调用异常
     */
    private <T extends BaseResponse> T executeInternal(final BaseRequest<T> request) throws ShadowBotApiException {
        // 请求地址
        String url = shadowProperties.getUrl().concat(request.getApiUrl());
        log.info("【请求地址】：{}", url);
        // 发送请求
        String response;
        ShadowBotResponse shadowBotResponse;
        try {
            // 请求头参数
            Map<String, String> headerMap = MapUtil.newHashMap();
            headerMap.put(ShadowBotConst.AUTHORIZATION_KEY, getToken());
            headerMap.put(ShadowBotConst.CONTENT_TYPE_KEY, ShadowBotConst.JSON);
            response = HttpUtil.createPost(url).addHeaders(headerMap).timeout(TIMEOUT).body(request.getJsonParams()).execute().body();
            log.info("【响应参数】：{}", response);
            shadowBotResponse = JSONUtil.toBean(response, ShadowBotResponse.class);
        } catch (Exception e) {
            log.error("调用影刀平台接口异", e);
            throw new ShadowBotApiException("调用影刀平台接口异常！");
        }
        if (Objects.isNull(shadowBotResponse)) {
            throw new ShadowBotApiException("调用影刀平台接口失败");
        }
        // 判断是否请求成功
        if (!shadowBotResponse.success()) {
            throw new ShadowBotApiException(shadowBotResponse.getCode(), shadowBotResponse.getMsg());
        }
        return shadowBotResponse.toData(request.getResponseClass());
    }

    /**
     * 获取token.
     * <p>
     * <a href="https://www.winrobot360.com/yddoc/language/zh-cn/%E7%AE%A1%E7%90%86%E6%96%87%E6%A1%A3/%E5%BC%80%E6%94%BEapi/api%E6%8E%A5%E5%8F%A3/%E9%89%B4%E6%9D%83.html">文档地址</a>
     * </p>
     *
     * @return token
     */
    private String getToken() {
        // 请求参数
        TokenCreateRequest request = new TokenCreateRequest();
        request.setAccessKeyId(shadowProperties.getAccessKeyId());
        request.setAccessKeySecret(shadowProperties.getAccessKeySecret());
        // 请求地址
        String url = shadowProperties.getUrl().concat(request.getApiUrl());
        // 发送请求
        String response = HttpUtil.createPost(url).form(request.getParams()).contentType(ContentType.FORM_URLENCODED.getValue()).execute().body();
        // 解析响应参数
        ShadowBotResponse shadowBotResponse = JSONUtil.toBean(response, ShadowBotResponse.class);
        if (Objects.isNull(shadowBotResponse) || !shadowBotResponse.success()) {
            log.error("调用影刀平台鉴权响应参数：{}", response);
            throw new ShadowBotRuntimeException("调用影刀平台鉴权失败！");
        }
        TokenCreateResponse tokenCreateResponse = shadowBotResponse.toData(request.getResponseClass());
        return ShadowBotConst.AUTHORIZATION_PREFIX.concat(tokenCreateResponse.getAccessToken());
    }

}
