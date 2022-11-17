package com.cloud.component.express;

import cn.hutool.core.convert.ConvertException;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cloud.common.utils.StringUtils;
import com.cloud.common.utils.json.JsonUtil;
import com.cloud.component.express.consts.ErrorCode;
import com.cloud.component.express.consts.ExpressCallbackStatusCode;
import com.cloud.component.express.consts.SubscribeExpressCode;
import com.cloud.component.express.domain.*;
import com.cloud.component.express.exception.ExpressException;
import com.cloud.component.properties.ExpressProperties;
import com.cloud.component.util.HttpClientUtil;
import com.google.gson.Gson;
import com.kuaidi100.sdk.api.Subscribe;
import com.kuaidi100.sdk.contant.ApiInfoConstant;
import com.kuaidi100.sdk.core.IBaseClient;
import com.kuaidi100.sdk.pojo.HttpResult;
import com.kuaidi100.sdk.request.SubscribeParam;
import com.kuaidi100.sdk.request.SubscribeParameters;
import com.kuaidi100.sdk.request.SubscribeReq;
import com.kuaidi100.sdk.response.SubscribeResp;
import com.kuaidi100.sdk.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
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

    /**
     * 校验订阅快递信息请求参数
     * @param subscribeExpressFrom 参数列表
     * @return Boolean
     */
    public Boolean checkSubscribeExpressFrom(SubscribeExpressFrom subscribeExpressFrom) {
        if (subscribeExpressFrom == null) {
            return false;
        }
        if (null == subscribeExpressFrom.getOrderId()) {
            return false;
        }
        if (StringUtils.isBlank(subscribeExpressFrom.getExpressCode())) {
            return false;
        }
        if (StringUtils.isBlank(subscribeExpressFrom.getExpressNo())) {
            return false;
        }
        if (StringUtils.isBlank(subscribeExpressFrom.getCellphone())) {
            return false;
        }
        return true;
    }

    /**
     * 订阅快递
     * @param subscribeExpressFrom 参数列表
     * @return String 订阅状态
     */
    public String subscribeExpress(SubscribeExpressFrom subscribeExpressFrom) {

        if (!checkSubscribeExpressFrom(subscribeExpressFrom)) {
            //订阅失败
            return SubscribeExpressCode.SUBSCRIPTION_FAIL.getCode();
        }
        //附加参数信息(回调地址，签名，是否开启行政区域解析等)
        SubscribeParameters parameter = getSubscribeParameters(subscribeExpressFrom);
        // 设置参数(设置key)
        SubscribeParam param = getSubscribeParam(parameter,subscribeExpressFrom);
        // 设置请求参数
        SubscribeReq request = getSubscribeReq();
        // 设置请求接口
        IBaseClient subscribe = new Subscribe();
        try {
            request.setParam(ChangeToJson(param));
            HttpResult httpResult = subscribe.execute(request);
            if (HttpStatus.HTTP_OK == httpResult.getStatus()) {
                String bodyData = httpResult.getBody();
                SubscribeResp response = new Gson().fromJson(httpResult.getBody(), SubscribeResp.class);
                String returnCode = response.getReturnCode();

                if(SubscribeExpressCode.REPEAT_SUBSCRIPTION.getCode().equals(returnCode)){
                    // 重复订阅的请求
                    return SubscribeExpressCode.REPEAT_SUBSCRIPTION.getCode();
                }
                if (StrUtil.isBlank(bodyData)) {
                    return SubscribeExpressCode.SUBSCRIPTION_FAIL.getCode();
                }
            } else {
                log.warn("调用订阅快递API接口响应失败:{}|{}", JsonUtil.toJson(subscribeExpressFrom),httpResult.getError());
                //请求失败 -> 订阅失败
                return SubscribeExpressCode.SUBSCRIPTION_FAIL.getCode();
            }
        } catch (Exception e) {
            return SubscribeExpressCode.SUBSCRIPTION_FAIL.getCode();
        }
        return SubscribeExpressCode.SUBSCRIPTION_SUCCESS.getCode();
    }

    public SubscribeParameters getSubscribeParameters(SubscribeExpressFrom subscribeExpressFrom){
        SubscribeParameters parameter = new SubscribeParameters();
        // 设置回调地址，在properties中配置
        String data = JsonUtil.toJson(subscribeExpressFrom);
        parameter.setCallbackurl(expressProperties.getCallbackUrl() + "?orderId=" + subscribeExpressFrom.getOrderId()
                + "&expressCode=" + subscribeExpressFrom.getExpressCode()
                + "&expressNo=" + subscribeExpressFrom.getExpressNo()
                + "&cellphone=" + subscribeExpressFrom.getCellphone());
        parameter.setSalt(sign(JsonUtil.toJson(subscribeExpressFrom) + expressProperties.getKey() + expressProperties.getCustomer()));
        parameter.setPhone(subscribeExpressFrom.getCellphone());
        //开通行政区域解析功能以及物流轨迹增加物流状态值
        parameter.setResultv2("4");
        return parameter;
    }

    public SubscribeParam getSubscribeParam(SubscribeParameters parameter, SubscribeExpressFrom subscribeExpressFrom){
        SubscribeParam param = new SubscribeParam();
        param.setKey(expressProperties.getKey());
        param.setParameters(parameter);
        param.setCompany(subscribeExpressFrom.getExpressCode());
        param.setNumber(subscribeExpressFrom.getExpressNo());

        return param;
    }

    public SubscribeReq getSubscribeReq(){
        SubscribeReq request = new SubscribeReq();
        request.setSchema(ApiInfoConstant.SUBSCRIBE_SCHEMA);
        return request;
    }

    public SubscribeResp getSubscribeResp(){
        SubscribeResp response = new SubscribeResp();
        response.setResult(Boolean.TRUE);
        response.setReturnCode("200");
        response.setMessage("成功");
        return response;
    }

    public String ChangeToJson(Object src) {
        return new Gson().toJson(src);
    }

    public boolean checkSign(SubscribeExpressFrom subscribeExpressFrom,String sign) {
        String ourSign = sign(JsonUtil.toJson(subscribeExpressFrom) + expressProperties.getKey() + expressProperties.getCustomer());
        if (ourSign.equals(sign)){
            return true;
        }
        return false;
    }

}
