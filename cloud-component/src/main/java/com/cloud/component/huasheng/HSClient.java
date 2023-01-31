package com.cloud.component.huasheng;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.cloud.common.exception.ServiceException;
import com.cloud.common.utils.json.JsonUtil;
import com.cloud.component.huasheng.consts.HSConst;
import com.cloud.component.huasheng.request.CreateOrdInfoParam;
import com.cloud.component.huasheng.request.LogisticsMessageParam;
import com.cloud.component.huasheng.response.OrderInfoResult;
import com.cloud.component.huasheng.response.StatusUpdateResult;
import com.cloud.component.huasheng.util.HSUtil;
import com.cloud.component.properties.HSProperties;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 华盛微零售客户端
 */
@Slf4j
public class HSClient {

    // 华盛配置属性
    private final HSProperties hsProperties;

    public HSClient(HSProperties hsProperties) {
        this.hsProperties = hsProperties;
    }

    /**
     * 订单状态修改（包含退款、退货审核、确认收货）
     * <p>
     * ordId		是	String	主订单号
     * ordSubId		否	String	子订单号
     * applyType	是	String	0-退款 1-退货 2-确认收货
     * backStatus	否	String	审核拒绝T5，审核通过T1（退货申请-审核通过-买家发货-退款成功），退款成功T4 applyType=2,backStatus为空
     * msg		    否	String	审核拒绝原因
     */
    public boolean orderStatusUpdate(String orderId, String ordSubId, String applyType, String backStatus, String msg) {
        log.info("调用 {} 接口, 参数 {} {} {} {} {}", HSConst.METHOD_CHANGE_ORDER_STATUS, orderId, ordSubId, applyType, backStatus, msg);
        // 必传参数添加校验
        if (StrUtil.isBlank(orderId) || StrUtil.isBlank(applyType)) {
            log.error("请求接口 {} 参数缺失", HSConst.METHOD_CHANGE_ORDER_STATUS);
            return false;
        }
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ordId", orderId);
        paramMap.put("applyType", applyType);
        if (StrUtil.isNotBlank(ordSubId)) paramMap.put("ordSubId", ordSubId);
        if (StrUtil.isNotBlank(backStatus)) paramMap.put("backStatus", backStatus);
        if (StrUtil.isNotBlank(msg)) paramMap.put("msg", msg);

        StatusUpdateResult response = HSUtil.doProcess(HSConst.METHOD_CHANGE_ORDER_STATUS, paramMap, StatusUpdateResult.class);
        StatusUpdateResult.Resp resp = response.getResp();
        log.info("调用 {} 接口, 结果 {}", HSConst.METHOD_CHANGE_ORDER_STATUS, JSONUtil.toJsonStr(resp));
        if (resp != null && "0".equals(resp.getCode())) {
            return true;
        }
        return false;
    }


    /**
     * 异步下单
     *
     * @param ordInfoParam
     */
    public OrderInfoResult.OrdInfo createOrderAsy(CreateOrdInfoParam ordInfoParam) {
        log.info("调用 {} 接口, 参数 {} ", HSConst.METHOD_CREATE_ORDER_ASY, JsonUtil.toJson(ordInfoParam));
        // 必传参数添加校验
        if (ordInfoParam == null) {
            throw new ServiceException("参数缺失");
        }

        HashMap<String, Object> paramMap = new HashMap<>(1);
        paramMap.put("ordInfo", ordInfoParam);

        Map<String, Object> param = new HashMap<>();
        param.put("body", paramMap);

        OrderInfoResult response = HSUtil.doProcess(HSConst.METHOD_CREATE_ORDER_ASY, param, OrderInfoResult.class);
        OrderInfoResult.Resp resp = response.getResp();
        log.info("调用 {} 接口, 结果 {}", HSConst.METHOD_CREATE_ORDER_ASY, JSONUtil.toJsonStr(resp));
        if (resp != null && "0".equals(resp.getCode())) {
            OrderInfoResult.OrdInfo result = resp.getResult();
            return result;
        }
        return null;
    }


    /**
     * 订单退款申请
     *
     * @param mobile   用户注册手机号 32
     * @param ordId    主订单号    32
     * @param backType 退货原因: 01-现在不想购买 02-商品价格较贵 03-价格波动 04-其他 2
     * @param backDesc 问题描述    512
     */
    public StatusUpdateResult orderBackMoney(String mobile, String ordId, String backType, String backDesc) {
        log.info("调用 {} 接口, 参数 {} {} {} {} ", HSConst.METHOD_ORDER_BACK_MONEY, mobile, ordId, backType, backDesc);
        // 必传参数添加校验
        if (StrUtil.isBlank(mobile) || StrUtil.isBlank(ordId) || StrUtil.isBlank(backType) || StrUtil.isBlank(backDesc)) {
            throw new ServiceException("参数缺失");
        }
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("mobile", mobile);
        paramMap.put("ordId", ordId);
        paramMap.put("backType", backType);
        paramMap.put("backDesc", backDesc);

        StatusUpdateResult response = HSUtil.doProcess(HSConst.METHOD_ORDER_BACK_MONEY, paramMap, StatusUpdateResult.class);
        StatusUpdateResult.Resp resp = response.getResp();
        log.info("调用 {} 接口, 结果 {}", HSConst.METHOD_ORDER_BACK_MONEY, JSONUtil.toJsonStr(resp));
        if (resp != null && "0".equals(resp.getCode())) {
            return response;
        }
        return null;
    }


    /**
     * 订单退货申请
     *
     * @param mobile   用户注册手机号 32
     * @param ordId    主订单号    32
     * @param ordSubId 子订单号    32
     * @param backType 退货原因: 01-收到商品破损 02-商品错发/漏发 03-收到商品与描述不符 04-商品质量问题 05-其他 2
     * @param backDesc 问题描述    512
     * @param pics     问题图片,多图片用竖线’|’分隔    512
     */
    public boolean orderBackGds(String mobile, String ordId, String ordSubId, String backType, String backDesc, String pics) {
        log.info("调用 {} 接口, 参数 {} {} {} {} {} {}", HSConst.METHOD_ORDER_BACK_GDS, mobile, ordId, ordSubId, backType, backDesc, pics);
        // 必传参数添加校验
        if (StrUtil.isBlank(mobile) || StrUtil.isBlank(ordId) || StrUtil.isBlank(ordSubId) || StrUtil.isBlank(backType) || StrUtil.isBlank(backDesc)) {
            throw new ServiceException("参数缺失");
        }
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("mobile", mobile);
        paramMap.put("ordId", ordId);
        paramMap.put("ordSubId", ordSubId);
        paramMap.put("backType", backType);
        paramMap.put("backDesc", backDesc);
        if (StrUtil.isNotBlank(pics)) paramMap.put("pics", pics);

        StatusUpdateResult response = HSUtil.doProcess(HSConst.METHOD_ORDER_BACK_GDS, paramMap, StatusUpdateResult.class);
        StatusUpdateResult.Resp resp = response.getResp();
        log.info("调用 {} 接口, 结果 {}", HSConst.METHOD_ORDER_BACK_GDS, JSONUtil.toJsonStr(resp));
        if (resp != null && "0".equals(resp.getCode())) {
            return true;
        }
        return false;
    }


    /**
     * 查询物流信息
     *
     * @param mobile 用户注册手机号 32
     * @param ordId  主订单号    32
     */
    public LogisticsMessageParam getExpress(String mobile, String ordId) {
        if (StrUtil.isBlank(mobile) || StrUtil.isBlank(ordId)) {
            throw new ServiceException("参数缺失");
        }
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("mobile", mobile);
        paramMap.put("ordId", ordId);

        StatusUpdateResult response = HSUtil.doProcess(HSConst.METHOD_GET_EXPRESS, paramMap, StatusUpdateResult.class);
        LogisticsMessageParam logisticsMessageVO = JSON.parseObject(JSONUtil.toJsonStr(response.getResp().getMsg()), LogisticsMessageParam.class);
        if (ObjectUtil.isEmpty(logisticsMessageVO)) {
            return null;
        }
        return logisticsMessageVO;
    }

    /**
     * 查询商品库存
     *
     * @param skuId 商品ID
     */
    public int getSkuStock(String skuId) {
        try {
            if (StrUtil.isBlank(skuId)) {
                throw new ServiceException("华盛查询商品库存接口：参数缺失");
            }
            // 拼接参数
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("skuId", skuId);
            // post请求
            StatusUpdateResult.Resp response = HSUtil.doProcess(HSConst.METHOD_QUERY_SKU_STOCK, paramMap, StatusUpdateResult.Resp.class);
            // 返回结果
            String result = response.getMsg();
            JSONObject jsonObject = JSONUtil.parseObj(result);
            return jsonObject.getInt("skuStock");
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

}
