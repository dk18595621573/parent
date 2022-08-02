package com.cloud.component.fadada;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.cloud.common.utils.DateUtils;
import com.cloud.common.utils.StringUtils;
import com.cloud.common.utils.json.JsonUtil;
import com.cloud.common.utils.sign.Base64;
import com.cloud.component.chinapay.util.Encryptor;
import com.cloud.component.fadada.request.*;
import com.cloud.component.fadada.response.*;
import com.cloud.component.properties.FadadaProperties;
import com.fadada.sdk.base.client.FddBaseClient;
import com.fadada.sdk.base.model.req.*;
import com.fadada.sdk.verify.client.FddVerifyClient;
import com.fadada.sdk.verify.model.req.ApplyCertParams;
import com.fadada.sdk.verify.model.req.CompanyVerifyUrlParams;
import com.fadada.sdk.verify.model.req.PersonVerifyUrlParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Map;
import java.util.Objects;

/**
 * 法大大接口工具
 *
 * @author mft
 */
@Slf4j
@AllArgsConstructor
public class FadadaClient {

    private final FddVerifyClient fddVerifyClient;
    private final FddBaseClient fddBaseClient;
    private final FadadaProperties fadadaProperties;

    /**
     * 1. 注册账号
     *
     * @param openId openId
     * @return 法大大返回参数
     */
    public FadadaDataResponse accountRegister(String openId) {
        RegisterAccountParams params = new RegisterAccountParams();
        // 账号类型 1、个人 2、企业
        params.setAccountType(fadadaProperties.getType());
        // 平台方自定义唯一标识
        params.setOpenId(openId);
        String result = fddBaseClient.invokeRegisterAccount(params);
        log.info("法大大返回参数，注册账号：{}", result);
        return JsonUtil.toPojo(JsonUtil.toMap(result), FadadaDataResponse.class);
    }

    /**
     * 2. 获取企业实名认证地址
     *
     * @return 法大大返回信息
     */
    public FadadaCompanyUrlResponse getCompanyVerifyUrl(CompanyVerifyUrlParams params) {
        String result = fddVerifyClient.invokeCompanyVerifyUrl(params);
        log.info("法大大返回参数，获取企业实名认证地址：{}", result);
        return JsonUtil.toPojo(JsonUtil.toMap(result), FadadaCompanyUrlResponse.class);
    }

    /**
     * 3. 获取个人实名认证地址
     */
    public FadadaDataResponse getPersonVerifyUrl(PersonVerifyUrlParams params) {
        //证件正面照图片文件
        params.setIdentFrontImg(new File(""));
        //证件反面照图片文件
        params.setIdentBackImg(new File(""));
        String result = fddVerifyClient.invokePersonVerifyUrl(params);
        log.info("法大大返回参数，获取个人实名认证地址：{}", result);
        return JsonUtil.toPojo(JsonUtil.toMap(result), FadadaDataResponse.class);
    }

    /**
     * 4. 实名证书申请
     */
    public FadadaDataResponse applyCert(ApplyCertParams params) {
        String result = fddVerifyClient.invokeApplyCert(params);
        log.info("法大大返回参数，实名证书申请：{}", result);
        return JsonUtil.toPojo(JsonUtil.toMap(result), FadadaDataResponse.class);
    }

    /**
     * 5. 印章上传
     */
    public FadadaDataResponse addSignature(SignatureRequest signatureRequest) {
        AddSignatureParams params = new AddSignatureParams();
        //客户编号 //下面章图片base64、签章图片、图片公网地址三选一
        params.setCustomerId(signatureRequest.getCustomerId());
        //签章图片base64
        params.setSignatureImgBase64(signatureRequest.getSignatureImgBase64());
        //签章图片
        params.setFile(new File("D:\\sign.png"));
        //签章图片公网地址
        params.setImgUrl("");
        String result = fddBaseClient.invokeAddSignature(params);
        log.info("法大大返回参数，印章上传：{}", result);
        return JsonUtil.toPojo(JsonUtil.toMap(result), FadadaDataResponse.class);
    }

    /**
     * 6. 自定义印章
     */
    public FadadaAddSignatureResponse customSignature(String customerId, String content) {
        CustomSignatureParams params = new CustomSignatureParams();
        //印章展示的文字
        params.setContent(content);
        //客户编号
        params.setCustomerId(customerId);
        String result = fddBaseClient.invokeCustomSignature(params);
        log.info("法大大返回参数，自定义印章：{}", result);
        Map<String, Object> stringObjectMap = JsonUtil.toMap(result);
        return JsonUtil.toPojo(JsonUtil.toMap(result), FadadaAddSignatureResponse.class);
    }

    /**
     * 7.合同上传
     * <p>
     * code:
     * <p>
     * 1000：操作成功
     * 2001：参数缺失或者不合法
     * 2002：业务异常，失败原因见msg
     * 2003：其他错误，请联系法大大
     */
    public FadadaResultResponse uploaddocs(UploaddocsRequest uploaddocsRequest) {
        UploadDocsParams params = new UploadDocsParams();
        params.setContractId(uploaddocsRequest.getContractId());
        //自定义合同id
        params.setDocTitle(uploaddocsRequest.getDocTitle());
        //合同标题
        //PDF文档
        params.setFile(new File(uploaddocsRequest.getFile()));
        //合同类型 目前仅支持pdf格式
        params.setDocType(uploaddocsRequest.getDocType());
        String result = fddBaseClient.invokeUploadDocs(params);
        log.info("法大大合同上传返回参数，合同上传：{}", result);
        return JsonUtil.toPojo(JsonUtil.toMap(result), FadadaResultResponse.class);
    }

    /**
     * 8.模板上传
     * <p>
     * code:
     * <p>
     * 1000：操作成功
     * 2001：参数缺失或者不合法
     * 2002：业务异常，失败原因见msg
     * 2003：其他错误，请联系法大大
     */
    public FadadaResultResponse uploadtemplate(UploadtemplateRequest uploadtemplateRequest) {
        UploadTemplateParams params = new UploadTemplateParams();
        // 平台自定义唯一模板编号
        // 下列PDF模板和文档地址二选一
        params.setTemplateId(uploadtemplateRequest.getTemplateId());
        params.setFile(new File(uploadtemplateRequest.getFile()));
        // PDF模板
        params.setDocUrl(uploadtemplateRequest.getDocUrl());
        // 文档地址
        String result = fddBaseClient.invokeUploadTemplate(params);
        log.info("法大大返回参数，模板上传：{}", result);
        return JsonUtil.toPojo(JsonUtil.toMap(result), FadadaResultResponse.class);
    }

    /**
     * 9.模板填充
     */
    public FadadaGenerateResponse generateContract(GenerateContractRequest generateContractRequest) {

        GenerateContractParams params = new GenerateContractParams();
        //模板编号
        params.setTemplateId(generateContractRequest.getTemplateId());
        //合同编号
        params.setContractId(generateContractRequest.getContractId());
        // 以下是非必填参数
        // 文档标题
        params.setDocTitle(generateContractRequest.getDocTitle());
        //字体大小
        params.setFontSize(generateContractRequest.getFontSize());
        //字体类型
        params.setFontType(generateContractRequest.getFontType());
        //填充内容,json字符串
        params.setParameterMap(parameter());
        //动态表格
        // 在版本号设置为 2.1 时，实现了对填充内容和动态表单进行3DES加密
//        params.setDynamicTables(aerodynamicTables());
//        fddBaseClient.setVersion("2.1");
        String result = fddBaseClient.invokeGenerateContract(params);
        log.info("法大大返回参数，模板填充：{}", result);
        return JsonUtil.toPojo(JsonUtil.toMap(result), FadadaGenerateResponse.class);
    }

    /**
     * 构造填充内容ParameterMap示例
     */
    private String parameter() {
        JSONObject parameter = new JSONObject();
        parameter.putOpt("Text1", "上海醒市");
        return parameter.toString();
    }

    /**
     * 构造动态表格DynamicTables示例
     */
    private String aerodynamicTables() {
        JSONArray dynamicTables = new JSONArray();
        JSONObject dynamic2 = new JSONObject();
        //0新页面插入 1在某个关键字后面插入
        dynamic2.putOpt("insertWay", 0);
        dynamic2.putOpt("pageBegin", "1");
        //页码从1开始计算
        dynamic2.putOpt("keyword", "表格插入处");
        //insertWay=1时，传关键字
        dynamic2.putOpt("cellHeight", "16.0");
        dynamic2.putOpt("colWidthPercent", new int[]{3, 4, 4, 4});
        dynamic2.putOpt("theFirstHeader", "附二");
        dynamic2.putOpt("cellHorizontalAlignment", "1");
        dynamic2.putOpt("cellVerticalAlignment", "5");
        dynamic2.putOpt("headers", new String[]{"序号", "借款人", "贷款人", "金额"});
        String[] row1 = new String[]{"1", "小网", "小易", "1000"};
        String[] row2 = new String[]{"2", "小云", "小音", "2000"};
        String[] row3 = new String[]{"3", "小乐", "天马", "3000"};
        dynamic2.putOpt("data", new String[][]{row1, row2, row3});
        dynamic2.putOpt("headersAlignment", "1");
        dynamic2.putOpt("tableWidthPercentage", 80);
        dynamicTables.add(dynamic2);
        System.out.println(dynamicTables);
        return dynamicTables.toString();
    }

    /**
     * 10.自动签署
     * <p>
     * code：
     * <p>
     * 1000：操作成功
     * 2001：参数缺失或者不合法
     * 2002：业务异常，失败原因见msg
     * 2003：其他错误，请联系法大大
     */
    public FadadaResultResponse extsignAuto() {
        ExtSignAutoParams params = new ExtSignAutoParams();
        //平台自定义唯一交易号
        params.setTransactionId("UUID12312414124");
        //此处传入调用上传或填充合同接口成功时定义的合同编号
        params.setContractId("UUIContraction123123");
        //此处传入认证成功后成功申请证书的客户编号
        params.setCustomerId("UUIContraction123123");
        params.setDocTitle("如：租赁合同协议");
        //0-关键字（默认）1-坐标
        params.setPositionType("0");
        params.setSignKeyword("出租人签字");
        //0：所有关键字签章 1：第一个关键字签章； 2：最后一个关键字签章
        params.setKeywordStrategy("0");
        String result = fddBaseClient.invokeExtSignAuto(params);
        log.info("法大大返回参数，自动签署：{}", result);
        return JsonUtil.toPojo(JsonUtil.toMap(result), FadadaResultResponse.class);
    }

    /**
     * 11.手动签署
     */
    public String extsign(ExtsignRequest extsignRequest) {
        ExtSignParams params = new ExtSignParams();
        //平台自定义唯一交易号
        params.setTransactionId(extsignRequest.getTransactionId());
        //此处传入调用上传或填充合同接口成功时定义的合同编号
        params.setContractId(extsignRequest.getContractId());
        //此处传入认证成功后成功申请证书的客户编号
        params.setCustomerId(extsignRequest.getCustomerId());
        params.setDocTitle(extsignRequest.getDocTitle());
        //0-关键字（默认）1-坐标
        params.setPositionType(extsignRequest.getPositionType());
        params.setSignKeyword(extsignRequest.getSignKeyword());
        //0-所有关键字签章 （默认） 1-第一个关键字签章 2-最后一个关键字签章
        params.setKeywordStrategy(extsignRequest.getKeywordStrategy());
        String result = fddBaseClient.invokeExtSign(params);
        log.info("法大大返回参数，手动签署：{}", result);
        return result;
    }

    /**
     * 12.合同查看
     *
     * @param contractId 合同编号
     */
    public String viewContract(String contractId) {
        ViewPdfURLParams params = new ViewPdfURLParams();
        //此处传入调用上传或填充合同接口成功 时定义的合同编号
        params.setContractId(contractId);
        String result = fddBaseClient.invokeViewPdfURL(params);
        log.info("法大大返回参数，合同查看：{}", result);
        return result;
    }

    /**
     * 13.合同下载
     *
     * @param contractId 合同编号
     *                   <p>
     * @return code:1000：操作成功
     * 2001：参数缺失或者不合法
     * 4001：无效交易号
     * 4002：文档已删除（已下载）
     * 4003：其他原因
     */
    public FadadaResultResponse downLoadContract(String contractId, String path) {
        DownloadPdfParams params = new DownloadPdfParams();
        // 合同编号
        params.setContractId(contractId);
        //如下，传setPath参数可以直接保存文件到本地，不传则返回url
        // 指定路径，如：D:\\pdf\\uuidNew.pdf
        params.setPath(path);
        String result = fddBaseClient.invokeDownloadPdf(params);
        log.info("法大大返回参数，合同下载：{}", result);
        return JsonUtil.toPojo(JsonUtil.toMap(result), FadadaResultResponse.class);
    }

    /**
     * 14.合同归档
     *
     * @param contractId 合同编号
     *                   <p>
     * @return code:1000：操作成功
     * 2001：参数缺失或者不合法
     * 2002：业务异常，失败原因见msg
     * 2003：其他错误，请联系法大大
     */
    public FadadaResultResponse contractFiling(String contractId) {
        ContractFillingParams params = new ContractFillingParams();
        //此处传入调用上传或填充合同接口成功时定义的合同编号
        params.setContractId(contractId);
        String result = fddBaseClient.invokeContractFilling(params);
        log.info("法大大返回参数，合同归档：{}", result);
        return JsonUtil.toPojo(JsonUtil.toMap(result), FadadaResultResponse.class);
    }

    /**
     * 摘要计算
     *
     * @param openId openId
     * @param type   账号类型 1、个人 2、企业
     * @return 加密后的摘要
     */
    public String abstracts(String openId, String type) {
        if (StringUtils.isEmpty(openId) || StringUtils.isEmpty(type)) {
            return null;
        }
        // 1.序列化需要参与
        String sort = String.format(type, openId);
        String format = String.format(fadadaProperties.getAppKey(), sort);
        // 2.对得到的字符串进⾏SHA1 加密
        String encode = Encryptor.encode(format, Encryptor.ALG_SHA1);
        // 3.计算 timestamp的MD5值，并和app_id，步骤 2 中得到的字符串拼接得到新的字符串
        String timestamp = DateUtil.format(DateUtil.date(), DateUtils.dateTimeNow());
        String format1 = String.format(fadadaProperties.getAddId(), Encryptor.encode(timestamp, Encryptor.ALG_SHA1), encode);
        // 4.对步骤3中得到的字符串进行SHA1加密
        String encode1 = Encryptor.encode(format1, Encryptor.ALG_SHA1);
        // 5.对步骤 4 中得到的字符串进⾏base64 加密
        String abstracts = null;
        if (Objects.nonNull(encode1)) {
            abstracts = Base64.encode(encode1.getBytes());
        }
        return abstracts;
    }
}
