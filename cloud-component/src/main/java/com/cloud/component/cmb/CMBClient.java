package com.cloud.component.cmb;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import com.cloud.common.utils.DateUtils;
import com.cloud.component.cmb.bean.request.BaseRequest;
import com.cloud.component.cmb.bean.request.CmbRequest;
import com.cloud.component.cmb.bean.request.account.AccountInfoReq;
import com.cloud.component.cmb.bean.request.account.AccountReq;
import com.cloud.component.cmb.bean.request.account.BatchBalanceReq;
import com.cloud.component.cmb.bean.request.account.BranchReq;
import com.cloud.component.cmb.bean.request.account.BusinessModelReq;
import com.cloud.component.cmb.bean.request.account.HistoryBalanceReq;
import com.cloud.component.cmb.bean.request.account.ReceiptReq;
import com.cloud.component.cmb.bean.request.account.ReceiptUrlReq;
import com.cloud.component.cmb.bean.request.account.SyncReceiptReq;
import com.cloud.component.cmb.bean.request.account.TransactionReq;
import com.cloud.component.cmb.bean.request.base.RequestHead;
import com.cloud.component.cmb.bean.request.pay.BatchPayInfoQueryReq;
import com.cloud.component.cmb.bean.request.pay.BatchPayQueryReq;
import com.cloud.component.cmb.bean.request.pay.BatchPayReq;
import com.cloud.component.cmb.bean.request.pay.SinglePayQueryReq;
import com.cloud.component.cmb.bean.request.pay.SinglePayReq;
import com.cloud.component.cmb.bean.response.CmbResponse;
import com.cloud.component.cmb.bean.response.account.AccountInfoRes;
import com.cloud.component.cmb.bean.response.account.AccountRes;
import com.cloud.component.cmb.bean.response.account.BatchBalanceRes;
import com.cloud.component.cmb.bean.response.account.BranchRes;
import com.cloud.component.cmb.bean.response.account.BusinessModelRes;
import com.cloud.component.cmb.bean.response.account.HistoryBalanceRes;
import com.cloud.component.cmb.bean.response.account.ReceiptRes;
import com.cloud.component.cmb.bean.response.account.ReceiptUrlRes;
import com.cloud.component.cmb.bean.response.account.SyncReceiptRes;
import com.cloud.component.cmb.bean.response.account.TransactionRes;
import com.cloud.component.cmb.bean.response.base.ResponseHead;
import com.cloud.component.cmb.bean.response.pay.BatchPayInfoQueryRes;
import com.cloud.component.cmb.bean.response.pay.BatchPayQueryRes;
import com.cloud.component.cmb.bean.response.pay.BatchPayRes;
import com.cloud.component.cmb.bean.response.pay.PaymentInfoRes;
import com.cloud.component.cmb.bean.response.pay.SinglePayRes;
import com.cloud.component.cmb.consts.CmbConst;
import com.cloud.component.cmb.exception.CmbApiException;
import com.cloud.component.cmb.exception.CmbException;
import com.cloud.component.cmb.util.CmbCryptor;
import com.cloud.component.cmb.util.CmbUtils;
import com.cloud.component.properties.CMBProperties;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 招商银行客户端.
 *
 * @author nlsm
 * @date 2023-3-6 16:34:15
 */
@Slf4j
public class CMBClient {

    /**
     * 编码.
     */
    private static final Base64.Encoder ENCODER = Base64.getEncoder();

    /**
     * 解码.
     */
    private static final Base64.Decoder DECODER = Base64.getDecoder();

    /**
     * 序列化工具.
     */
    private static final Gson GSON = new Gson();

    private final CMBProperties cmbProperties;

    public CMBClient(CMBProperties cmbProperties) {
        this.cmbProperties = cmbProperties;
    }

    /**
     * 可经办业务模式查询.
     *
     * @param request 请求参数
     * @return 结果
     */
    public BusinessModelRes getBusinessModel(final BusinessModelReq request) throws CmbApiException {
        Assert.notNull(request, "请求参数不能为空");
        log.info("[招商银行] - 可经办业务模式查询请求参数：{}", request.getJsonParams());
        // 执行请求调度
        CmbResponse response = this.executeInternal(request);
        return response.toBody(BusinessModelRes.class);
    }

    /**
     * 查询可经办的账户列表.
     *
     * @param request 请求参数
     * @return 结果
     */
    public AccountRes getAccountList(final AccountReq request) throws CmbApiException {
        Assert.notNull(request, "请求参数不能为空");
        log.info("[招商银行] - 查询可经办的账户列表请求参数：{}", request.getJsonParams());
        // 执行请求调度
        CmbResponse response = this.executeInternal(request);
        return response.toBody(AccountRes.class);
    }

    /**
     * 账户详细信息查询.
     *
     * @param request 请求参数
     * @return 结果
     */
    public AccountInfoRes getAccountInfo(final AccountInfoReq request) throws CmbApiException {
        Assert.notNull(request, "请求参数不能为空");
        log.info("[招商银行] - 账户详细信息查询请求参数：{}", request.getJsonParams());
        // 执行请求调度
        CmbResponse response = this.executeInternal(request);
        return response.toBody(AccountInfoRes.class);
    }

    /**
     * 查询分行号信息.
     *
     * @param request 请求参数
     * @return 结果
     */
    public BranchRes getBranch(final BranchReq request) throws CmbApiException {
        Assert.notNull(request, "请求参数不能为空");
        log.info("[招商银行] - 查询分行号信息请求参数：{}", request.getJsonParams());
        // 执行请求调度
        CmbResponse response = this.executeInternal(request);
        return response.toBody(BranchRes.class);
    }

    /**
     * 查询账户历史余额.
     *
     * @param request 请求参数
     * @return 结果
     */
    public HistoryBalanceRes getHistoryBalance(final HistoryBalanceReq request) throws CmbApiException {
        Assert.notNull(request, "请求参数不能为空");
        log.info("[招商银行] - 查询账户历史余额请求参数：{}", request.getJsonParams());
        // 执行请求调度
        CmbResponse response = this.executeInternal(request);
        return response.toBody(HistoryBalanceRes.class);
    }

    /**
     * 批量查询余额.
     *
     * @param request 请求参数
     * @return 结果
     */
    public BatchBalanceRes batchBalance(final BatchBalanceReq request) throws CmbApiException {
        Assert.notNull(request, "请求参数不能为空");
        log.info("[招商银行] - 批量查询余额请求参数：{}", request.getJsonParams());
        // 执行请求调度
        CmbResponse response = this.executeInternal(request);
        return response.toBody(BatchBalanceRes.class);
    }

    /**
     * 账户交易信息查询.
     *
     * @param request 请求参数
     * @return 结果
     */
    public TransactionRes getTransaction(final TransactionReq request) throws CmbApiException {
        Assert.notNull(request, "请求参数不能为空");
        log.info("[招商银行] - 账户交易信息查询请求参数：{}", request.getJsonParams());
        // 执行请求调度
        CmbResponse response = this.executeInternal(request);
        return response.toBody(TransactionRes.class);
    }

    /**
     * 单笔回单查询.
     *
     * @param request 请求参数
     * @return 结果
     */
    public ReceiptRes getReceipt(final ReceiptReq request) throws CmbApiException {
        Assert.notNull(request, "请求参数不能为空");
        log.info("[招商银行] - 单笔回单查询请求参数：{}", request.getJsonParams());
        // 执行请求调度
        CmbResponse response = this.executeInternal(request);
        return response.toBody(ReceiptRes.class);
    }

    /**
     * 电子回单异步查询.
     *
     * @param request 请求参数
     * @return 结果
     */
    public SyncReceiptRes getSyncReceipt(final SyncReceiptReq request) throws CmbApiException {
        Assert.notNull(request, "请求参数不能为空");
        log.info("[招商银行] - 电子回单异步查询请求参数：{}", request.getJsonParams());
        // 执行请求调度
        CmbResponse response = this.executeInternal(request);
        return response.toBody(SyncReceiptRes.class);
    }

    /**
     * 回单异步打印结果查询.
     *
     * @param request 请求参数
     * @return 结果
     */
    public ReceiptUrlRes getReceiptUrl(final ReceiptUrlReq request) throws CmbApiException {
        Assert.notNull(request, "请求参数不能为空");
        log.info("[招商银行] - 回单异步打印结果查询请求参数：{}", request.getJsonParams());
        // 执行请求调度
        CmbResponse response = this.executeInternal(request);
        return response.toBody(ReceiptUrlRes.class);
    }

    /**
     * 企银支付单笔经办.
     *
     * @param request 请求参数
     * @return 结果
     */
    public SinglePayRes singlePay(final SinglePayReq request) throws CmbApiException {
        Assert.notNull(request, "请求参数不能为空");
        log.info("[招商银行] - 企银支付单笔经办请求参数：{}", request.getJsonParams());
        // 执行请求调度
        CmbResponse response = this.executeInternal(request);
        return response.toBody(SinglePayRes.class);
    }

    /**
     * 企银支付业务查询
     *
     * @param request 请求参数
     * @return 结果
     */
    public PaymentInfoRes getSinglePay(final SinglePayQueryReq request) throws CmbApiException {
        Assert.notNull(request, "请求参数不能为空");
        log.info("[招商银行] - 企银支付业务查询请求参数：{}", request.getJsonParams());
        // 执行请求调度
        CmbResponse response = this.executeInternal(request);
        return response.toBody(PaymentInfoRes.class);
    }

    /**
     * 企银支付批量经办.
     *
     * @param request 请求参数
     * @return 结果
     */
    public BatchPayRes batchPay(final BatchPayReq request) throws CmbApiException {
        Assert.notNull(request, "请求参数不能为空");
        log.info("[招商银行] - 企银支付批量经办请求参数：{}", request.getJsonParams());
        // 执行请求调度
        CmbResponse response = this.executeInternal(request);
        return response.toBody(BatchPayRes.class);
    }

    /**
     * 企银批量支付批次查询.
     *
     * @param request 请求参数
     * @return 结果
     */
    public BatchPayQueryRes getBatchPay(final BatchPayQueryReq request) throws CmbApiException {
        Assert.notNull(request, "请求参数不能为空");
        log.info("[招商银行] - 企银批量支付批次查询请求参数：{}", request.getJsonParams());
        // 执行请求调度
        CmbResponse response = this.executeInternal(request);
        return response.toBody(BatchPayQueryRes.class);
    }

    /**
     * 企银批量支付明细查询.
     *
     * @param request 请求参数
     * @return 结果
     */
    public BatchPayInfoQueryRes getBatchPayInfo(final BatchPayInfoQueryReq request) throws CmbApiException {
        Assert.notNull(request, "请求参数不能为空");
        log.info("[招商银行] - 企银批量支付明细查询请求参数：{}", request.getJsonParams());
        // 执行请求调度
        CmbResponse response = this.executeInternal(request);
        return response.toBody(BatchPayInfoQueryRes.class);
    }

    /**
     * 执行支付请求调用.
     *
     * @param request 请求
     * @param <T>     具体的API响应类
     * @return 具体的API响应结果
     * @throws CmbApiException API调用异常
     */
    private <T> CmbResponse executeInternal(final BaseRequest<T> request) throws CmbApiException {
        // 构建请求参数
        CmbRequest cmbRequest = this.buildCmbRequest(request);
        // 接口请求参数
        HashMap<String, String> map = MapUtil.newHashMap();
        map.put(CmbConst.UID_KEY, cmbProperties.getUID());
        map.put(CmbConst.FUNCODE_KEY, request.getFuncode());
        map.put(CmbConst.ALG_KEY, CmbConst.ALG_SM);
        map.put(CmbConst.DATA_KEY, URLEncoder.encode(this.getSignData(BeanUtil.beanToMap(cmbRequest)), StandardCharsets.UTF_8));
        // 发起请求
        return this.postObject(map);
    }

    /**
     * 发送POST请求，得到响应对象.
     *
     * @param params 请求参数
     * @return 请求结果
     * @throws CmbApiException 接口异常
     */
    private CmbResponse postObject(final Map<String, String> params) throws CmbApiException {
        // 请求地址
        String url = cmbProperties.getURL();
        log.info("【请求地址】：{}", url);
        // 发送请求
        String decryptData;
        try {
            String response = CmbUtils.doPostForm(url, params);
            log.info("【响应参数】：{}", response);
            DECODER.decode(response);
            // 解密响应数据
            decryptData = new String(CmbCryptor.CMBSM4DecryptWithCBC(cmbProperties.getAesKey().getBytes(), getVector(cmbProperties.getUID()), DECODER.decode(response)), StandardCharsets.UTF_8);
            log.info("【解密响应数据】：\n{} ", JSONUtil.parse(decryptData).toStringPretty());
        } catch (Exception e) {
            log.error("调用招商银行接口异常：{}", ExceptionUtils.getStackTrace(e));
            throw new CmbApiException("调用招商银行接口异常");
        }
        // 验证签名
        this.verifySign(decryptData);
        // 数据转换
        CmbResponse cmbResponse = JSONUtil.toBean(decryptData, CmbResponse.class);
        // 获取响应头
        ResponseHead head = cmbResponse.toHead();
        // 判断是否请求成功
        if (!head.success()) {
            throw new CmbApiException(head.getResultcode(), head.getResultmsg());
        }
        return cmbResponse;
    }

    /**
     * 构建请求参数.
     *
     * @param request 请求参数
     * @param <T>     泛型
     * @return 结果
     */
    private <T> CmbRequest buildCmbRequest(final BaseRequest<T> request) {
        // 构建请求参数
        CmbRequest cmbRequest = new CmbRequest();
        CmbRequest.Request<T> objectRequest = new CmbRequest.Request<>();
        // 请求头
        RequestHead head = new RequestHead();
        head.setFuncode(request.getFuncode());
        head.setReqid(CmbUtils.getTimestamp());
        head.setUserid(cmbProperties.getUID());
        objectRequest.setHead(head);
        // 请求体
        objectRequest.setBody(JSONUtil.parseObj(request.getJsonParams()));
        // 请求参数
        cmbRequest.setRequest(objectRequest);
        log.info("【请求报文】：\n{} ", JSONUtil.parse(cmbRequest).toStringPretty());
        return cmbRequest;
    }

    /**
     * 获取签名数据.
     *
     * @param params 请求参数
     * @return 签名数据
     */
    private String getSignData(final Map<String, Object> params) {
        try {
            // 转换成Json对象
            JsonObject paramsObject = GSON.fromJson(JSONUtil.toJsonStr(params), JsonObject.class);
            // 签名信息
            JsonObject signature = new JsonObject();
            signature.addProperty(CmbConst.SIGDAT_KEY, CmbConst.SIGDAT_PRE);
            signature.addProperty(CmbConst.SIGTIM_KEY, DateUtils.dateTimeNow());
            paramsObject.add(CmbConst.SIGNATURE_KEY, signature);
            // 生成签名
            String source = CmbUtils.serialJsonOrdered(paramsObject);
            byte[] sign = CmbCryptor.CMBSM2SignWithSM3(getVector(cmbProperties.getUID()), DECODER.decode(cmbProperties.getPrivateKey()), source.getBytes(StandardCharsets.UTF_8));
            String sigdat = new String(ENCODER.encode(sign));
            signature.addProperty(CmbConst.SIGDAT_KEY, sigdat);
            // SM4-CBC加密
            String plainTxt = paramsObject.toString();
            byte[] enInput = CmbCryptor.CMBSM4EncryptWithCBC(cmbProperties.getAesKey().getBytes(), getVector(cmbProperties.getUID()), plainTxt.getBytes(StandardCharsets.UTF_8));
            return new String(ENCODER.encode(enInput));
        } catch (Exception e) {
            log.error("获取签名数据异常：{}", ExceptionUtils.getStackTrace(e));
            throw new CmbException("获取签名数据异常");
        }
    }

    /**
     * 验证签名.
     *
     * @param data 数据
     * @throws CmbApiException 异常
     */
    private void verifySign(String data) throws CmbApiException {
        boolean verify;
        try {
            // 验签
            JsonObject fromJson = GSON.fromJson(data, JsonObject.class);
            JsonObject signature = fromJson.getAsJsonObject(CmbConst.SIGNATURE_KEY);
            String resSign = signature.get(CmbConst.SIGDAT_KEY).getAsString();
            log.info("【响应签名】：{} ", resSign);
            signature.addProperty(CmbConst.SIGDAT_KEY, CmbConst.SIGDAT_PRE);
            fromJson.add(CmbConst.SIGNATURE_KEY, signature);
            String resSignSource = CmbUtils.serialJsonOrdered(fromJson);
            verify = CmbCryptor.CMBSM2VerifyWithSM3(getVector(cmbProperties.getUID()), DECODER.decode(cmbProperties.getPublicKey()), resSignSource.getBytes(StandardCharsets.UTF_8), DECODER.decode(resSign));
        } catch (Exception e) {
            log.error("验签异常：{}", ExceptionUtils.getStackTrace(e));
            throw new CmbException("验签异常");
        }
        if (!verify) {
            throw new CmbApiException("验签失败,伪造的签名！");
        }
    }

    /**
     * 获取向量参数.
     * 注意：国密算法中会用到向量参数，该参数值长度16位，取值为用户ID，不足16位的右边补0
     *
     * @param uid 用户ID
     * @return 向量参数
     */
    private static byte[] getVector(String uid) {
        String userid = uid + "0000000000000000";
        return userid.substring(0, 16).getBytes();
    }

}
