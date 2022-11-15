package com.cloud.component.express;

import cn.hutool.core.convert.ConvertException;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.cloud.common.utils.json.JsonUtil;
import com.cloud.component.express.consts.ErrorCode;
import com.cloud.component.express.domain.CallbackExpressResult;
import com.cloud.component.express.domain.ExpressResult;
import com.cloud.component.express.domain.SubscribeExpressResult;
import com.cloud.component.express.exception.ExpressException;
import com.cloud.component.properties.ExpressProperties;
import com.cloud.component.util.HttpClientUtil;
import com.google.gson.Gson;
import com.kuaidi100.sdk.api.Subscribe;
import com.kuaidi100.sdk.contant.ApiInfoConstant;
import com.kuaidi100.sdk.contant.CompanyConstant;
import com.kuaidi100.sdk.core.IBaseClient;
import com.kuaidi100.sdk.pojo.HttpResult;
import com.kuaidi100.sdk.request.SubscribeParam;
import com.kuaidi100.sdk.request.SubscribeParameters;
import com.kuaidi100.sdk.request.SubscribeReq;
import com.kuaidi100.sdk.response.SubscribeResp;
import com.kuaidi100.sdk.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 快递信息查询.
 *
 * @author zenghao
 * @date 2022/7/19
 */
@Slf4j
public class ExpressClient {

    /**
     * 接口超时时间
     */
    private static final int TIMEOUT = 5000;

    private final ExpressProperties expressProperties;

    public ExpressClient(final ExpressProperties expressProperties) {
        this.expressProperties = expressProperties;
    }

    /**
     * 快递100 查询快递信息接口
     *
     * @param expressCode
     * @param expressNo
     * @param cellphone
     * @return
     */
    public ExpressResult findExpress(final String expressCode, final String expressNo, final String cellphone) {
        String expressNoN = expressNo.replaceAll(" ", "").replaceAll("[\\p{Cf}]", "");;
        Map<String, String> paramMap = MapUtil.newHashMap(4);
        // 结果排序
        paramMap.put("order", "asc");
        // 快递公司
        paramMap.put("com", expressCode);
        // 快递单号
        paramMap.put("num", expressNoN);
        // 手机号码
        paramMap.put("phone", cellphone);
        // 开通行政区域解析功能
        paramMap.put("resultv2", "4");
        String param = JsonUtil.toJson(paramMap);
        Map<String, String> requestBody = MapUtil.newHashMap(3);
        // 授权码，请申请企业版获取
        requestBody.put("customer", expressProperties.getCustomer());
        requestBody.put("sign", sign(param + expressProperties.getKey() + expressProperties.getCustomer()));
        requestBody.put("param", param);

        ExpressResult expressResult = null;
        try {
            String result = HttpClientUtil.doHttpPost(expressProperties.getUrl(), requestBody);
            log.info("调用快递API返回单号[{}]的快递信息数据：{}", expressNo, result);
            if (StrUtil.isBlank(result)) {
                throw new ExpressException(ErrorCode.API_ERROR);
            }
            JSONObject jsonObject = JSONUtil.parseObj(result);
            String returnCode = jsonObject.getStr("returnCode");
            if (StrUtil.isNotBlank(returnCode)) {
                log.warn("调用快递API接口响应失败:{}", jsonObject);
                throw new ExpressException(ErrorCode.fromCode(returnCode));
            }
            expressResult = JSONUtil.toBean(jsonObject, ExpressResult.class);
        } catch (HttpException e) {
            log.warn("调用快递API接口异常[{}]:{}", expressNo, e.getMessage());
            throw new ExpressException(ErrorCode.API_ERROR);
        } catch (ConvertException e) {
            log.warn("调用快递API单号[{}]响应的快递数据转换异常:{}", expressNo, e);
            throw new ExpressException(ErrorCode.API_ERROR);
        }
        //查询失败状态处理
        return expressResult;
    }

    /**
     * 快递100加密方式统一为MD5后转大写
     *
     * @param msg
     * @return
     */
    public static String sign(String msg) {
        return SecureUtil.md5(msg).toUpperCase();
    }

    public static void main(String[] args) {
        ExpressProperties properties = new ExpressProperties();
        properties.setUrl("https://poll.kuaidi100.com/poll/query.do");
        properties.setKey("wBfjOuYf6894");
        properties.setCustomer("C7AF17641A07E84D1C93C43645515C69");

        ExpressClient expressClient = new ExpressClient(properties);
        new Thread(() -> {
            ExpressResult express2 = expressClient.findExpress("shunfeng", "SF1358981020011", "15325014087");
            System.out.println(express2);
        }).start();

        new Thread(() -> {
            ExpressResult express2 = expressClient.findExpress("shunfeng", "SF1358981020011", "15325014087");
            System.out.println(express2);
        }).start();

        new Thread(() -> {
            ExpressResult express2 = expressClient.findExpress("shunfeng", "SF1358981020011", "15325014087");
            System.out.println(express2);
        }).start();

        new Thread(() -> {
            ExpressResult express2 = expressClient.findExpress("shunfeng", "SF1358981020011", "15325014087");
            System.out.println(express2);
        }).start();

    }

    public void subscribeExpress(final String orderCode,final String expressCode, final String expressNo, final String cellphone) {
        SubscribeParameters subscribeParameters = new SubscribeParameters();
//        subscribeParameters.setCallbackurl(expressProperties.getCallbackUrl());
        subscribeParameters.setCallbackurl("https://test-api.xingshiapp.cn/api/oms/express/callbackExpress");
        subscribeParameters.setPhone(cellphone);
        subscribeParameters.setSalt(SignUtils.sign(expressProperties.getKey() + expressProperties.getCustomer()));
        SubscribeParam subscribeParam = new SubscribeParam();
        subscribeParam.setParameters(subscribeParameters);
        subscribeParam.setCompany(expressCode);
        subscribeParam.setNumber(expressNo);
        subscribeParam.setKey(expressProperties.getKey());

        SubscribeReq subscribeReq = new SubscribeReq();
        subscribeReq.setSchema(ApiInfoConstant.SUBSCRIBE_SCHEMA);
        subscribeReq.setParam(new Gson().toJson(subscribeParam));

        try {
            IBaseClient subscribe = new Subscribe();
            HttpResult execute = subscribe.execute(subscribeReq);
            if (HttpStatus.HTTP_OK == execute.getStatus()) {
                String bodyData = execute.getBody();
                if (StrUtil.isBlank(bodyData)) {
                    throw new ExpressException(ErrorCode.API_ERROR);
                }
                SubscribeExpressResult subscribeExpressResult = JSON.parseObject(bodyData, SubscribeExpressResult.class);
                if (subscribeExpressResult.getResult() != null ) {
                    //订阅成功 修改订单订阅状态
                }
            } else {
                log.warn("调用订阅快递API接口响应失败:{}", expressNo,execute.getError());
                //请求失败 -> 订阅失败
                System.out.println("请求失败：status=" + execute.getStatus()+"error=" + execute.getError());
            }
        } catch (Exception e) {
            throw new ExpressException(ErrorCode.API_ERROR);
        }
    }
    public void callbackExpress(String jsonStr) {
        log.info("快递回调传参：{}",jsonStr);
    }
}
