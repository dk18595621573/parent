package com.cloud.component.fadada;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.cloud.common.utils.DateUtils;
import com.cloud.common.utils.StringUtils;
import com.cloud.common.utils.json.JsonUtil;
import com.cloud.common.utils.sign.Base64;
import com.cloud.common.utils.uuid.IdUtils;
import com.cloud.component.chinapay.util.Encryptor;
import com.cloud.component.fadada.consts.FadadaStatusCode;
import com.cloud.component.fadada.request.*;
import com.cloud.component.fadada.response.*;
import com.cloud.component.properties.FadadaProperties;
import com.fadada.sdk.base.client.FddBaseClient;
import com.fadada.sdk.base.model.req.*;
import com.fadada.sdk.extra.client.FddExtraClient;
import com.fadada.sdk.extra.model.req.*;
import com.fadada.sdk.utils.crypt.CryptTool;
import com.fadada.sdk.utils.crypt.FddEncryptTool;
import com.fadada.sdk.verify.client.FddVerifyClient;
import com.fadada.sdk.verify.model.req.ApplyCertParams;
import com.fadada.sdk.verify.model.req.CompanyVerifyUrlParams;
import com.fadada.sdk.verify.model.req.PersonVerifyUrlParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
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

    private final FddExtraClient fddExtraClient;

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
        return JsonUtil.parse(result, FadadaDataResponse.class);
    }

    /**
     * 2. 获取企业实名认证地址
     *
     * @return 法大大返回信息
     */
    public FadadaCompanyUrlResponse getCompanyVerifyUrl(CompanyVerifyUrlParams params) {
        String result = fddVerifyClient.invokeCompanyVerifyUrl(params);
        log.info("法大大返回参数，获取企业实名认证地址：{}", result);
        return JsonUtil.parse(result, FadadaCompanyUrlResponse.class);
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
        return JsonUtil.parse(result, FadadaDataResponse.class);
    }

    /**
     * 4. 实名证书申请
     */
    public FadadaDataResponse applyCert(ApplyCertParams params) {
        String result = fddVerifyClient.invokeApplyCert(params);
        log.info("法大大返回参数，实名证书申请：{}", result);
        return JsonUtil.parse(result, FadadaDataResponse.class);
    }

    /**
     * 5. 印章上传
     */
    public FadadaAddSignatureResponse addSignature(SignatureRequest signatureRequest) {
        AddSignatureParams params = new AddSignatureParams();
        //客户编号 //下面章图片base64、签章图片、图片公网地址三选一
        params.setCustomerId(signatureRequest.getCustomerId());
        //签章图片base64
        params.setSignatureImgBase64(signatureRequest.getSignatureImgBase64());
        //签章图片
        params.setFile(signatureRequest.getFile());
        //签章图片公网地址
        params.setImgUrl(signatureRequest.getImgUrl());
        String result = fddBaseClient.invokeAddSignature(params);
        log.info("法大大返回参数，印章上传：{}", result);
        return JsonUtil.parse(StringUtils.toCamelCase(result), FadadaAddSignatureResponse.class);
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
        return JsonUtil.parse(result, FadadaAddSignatureResponse.class);
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
        params.setFile(uploaddocsRequest.getFile());
        //合同类型 目前仅支持pdf格式
        params.setDocType(uploaddocsRequest.getDocType());
        String result = fddBaseClient.invokeUploadDocs(params);
        log.info("法大大合同上传返回参数，合同上传：{}", result);
        return JsonUtil.parse(result, FadadaResultResponse.class);
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
        params.setFile(uploadtemplateRequest.getFile());
        // PDF模板
        params.setDocUrl(uploadtemplateRequest.getDocUrl());
        // 文档地址
        String result = fddBaseClient.invokeUploadTemplate(params);
        log.info("法大大返回参数，模板上传：{}", result);
        return JsonUtil.parse(result, FadadaResultResponse.class);
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
        params.setParameterMap(generateContractRequest.getParameterMap());
        //动态表格
        // 在版本号设置为 2.1 时，实现了对填充内容和动态表单进行3DES加密
//        params.setDynamicTables(aerodynamicTables());
//        fddBaseClient.setVersion("2.1");
        String result = fddBaseClient.invokeGenerateContract(params);
        log.info("法大大返回参数，模板填充：{}", result);
        return JsonUtil.parse(StringUtils.toCamelCase(result), FadadaGenerateResponse.class);
    }

    /**
     * 构造填充内容ParameterMap示例
     */
    private String parameter() {
        JSONObject parameter = new JSONObject();
        parameter.putOpt("companyName", "上海醒市");
        parameter.putOpt("userName", "爪哇");
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
        params.setKeywordStrategy("2");
        String result = fddBaseClient.invokeExtSignAuto(params);
        log.info("法大大返回参数，自动签署：{}", result);
        return JsonUtil.parse(result, FadadaResultResponse.class);
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
        // 签署结果异步通知url
        params.setNotifyUrl(extsignRequest.getNotifyUrl());
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
        //此处传入调用上传或填充合同接口成功时定义的合同编号
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
        return JsonUtil.parse(result, FadadaResultResponse.class);
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
        return JsonUtil.parse(result, FadadaResultResponse.class);
    }

    /**
     * 上传合同模板
     */
    public String uploadtemplate(UploadTemplateDocsParams params) {
        String result = fddExtraClient.invokeUploadTemplateDocs(params);
        log.info("法大大返回参数，上传合同模板：{}", result);
        return result;
    }

    /**
     * 编辑页面
     */
    public String getDocStream(GetDocStreamParams params) {
        String result = fddExtraClient.invokeGetDocStream(params);
        log.info("法大大返回参数，编辑页面：{}", result);
        return result;
    }

    /**
     * 多方填充地址
     */
    public String getTemplatePage(GetTemplatePageParams params) {
        //多方填充的模板
        String result = fddExtraClient.invokeGetTemplatePage(params);
        log.info("法大大返回参数，多方填充地址：{}", result);
        return result;
    }

    /**
     * 自定义短信
     */
    public FadadaDataResponse smsText(SmsTextParams params) {
        // 手机号
        params.setMobile(FddEncryptTool.encrypt(params.getMobile(), fadadaProperties.getAppKey()));
        // 短信类型 1：填充模板 2：自定义内容
        params.setMessageType(params.getMessageType());
        // 短信内容
        params.setMessageContent(params.getMessageContent());
        // 验证码
        params.setCode(params.getCode());
        String result = fddExtraClient.invokeSmsText(params);
        log.info("法大大返回参数，自定义短信：{}", result);
        return JsonUtil.parse(result, FadadaDataResponse.class);
    }
    public FadadaDataResponse smsShortUrl(PushShortUrlSmsParams pushShortUrlParams) {
        PushShortUrlSmsParams params = new PushShortUrlSmsParams();
        //原始链接，不超3000位
        params.setSourceUrl(pushShortUrlParams.getSourceUrl());
        //有效时间，单位min，最长不超过10080
        params.setExpireTime(pushShortUrlParams.getExpireTime());
        //手机号,使用3DES对数据加密：3DES(手机号,接入方秘钥)
        params.setMobile(CryptTool.encrypt(pushShortUrlParams.getMobile(), fadadaProperties.getAppKey()));
        //短信类型1填充模板 2自定义模板
        // 1填充模板：云端填充短信模板生成短信内容并发送。详情见sms_template_type
        //2自定义模板：调用方传message_content，云端将其发送给用户
        params.setMessageType(pushShortUrlParams.getMessageType());
        //短信模板:message_type为1时候不能为空
        //1：签署时候推送短链
        //短信模板内容：您有一份来自[${platform}]的合同需要签署，请点击下面的签署链接完成合同签署！${shortUrl}
        //2：实名认证时候推送短链
        //短信模板内容：[${platform}]邀请您进行身份认证，请点击链接开始认证${shortUrl}
        params.setSmsTemplateType(pushShortUrlParams.getSmsTemplateType());
        //自定义短信模板内容：message_type为2时候不能为空
        // 1.如：[${platform}]您要推送的短链是${shortUrl} 建议不超过500位
        // 2.不能存在【】中括号，【】会被拦截，可以使用<>代替。
        params.setMessageContent(pushShortUrlParams.getMessageContent());
        String result = fddExtraClient.invokePushShortUrlSms(params);
        return JsonUtil.parse(result, FadadaDataResponse.class);
    }

    /**
     * 转为短连接
     */
    public FadadaDataResponse shortUrl(ShortUrlParams params) {
        // 过期时间（默认七天后过期，单位：分钟）
        // params.setExpireTime("120");
        // 最长500个字符
        // params.setSourceUrl("https://www.fadada.com/");
        String result = fddExtraClient.invokeShortUrl(params);
        log.info("法大大返回参数，短连接：{}", result);
        return JsonUtil.parse(result, FadadaDataResponse.class);
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

    /**
     * 拒签
     *
     * @param transactionId 交易号
     * @param contractId   合同id
     * @param customerId   客户编号
     * @param rejectReason   拒签理由
     * @return 加密后的摘要
     */
    public FadadaDataResponse refusal(String transactionId, String contractId, String customerId, String rejectReason) {
        ContractRejectSignParams params = new ContractRejectSignParams();
        params.setContractId(contractId);
        params.setCustomerId(customerId);
        params.setRejectReason(rejectReason);
        params.setTransactionId(transactionId);
        String result = fddExtraClient.invokeContractRejectSign(params);
        return JsonUtil.parse(result, FadadaDataResponse.class);
    }
}
