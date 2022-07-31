package com.cloud.component.fadada;

import cn.hutool.core.date.DateUtil;
import com.cloud.common.utils.DateUtils;
import com.cloud.common.utils.StringUtils;
import com.cloud.common.utils.json.JsonUtil;
import com.cloud.common.utils.sign.Base64;
import com.cloud.component.chinapay.util.Encryptor;
import com.cloud.component.fadada.request.CompanyVerifyRequest;
import com.cloud.component.fadada.request.SignatureRequest;
import com.cloud.component.fadada.request.UploadtemplateRequest;
import com.cloud.component.fadada.response.FadadaCompanyUrlResponse;
import com.cloud.component.fadada.response.FadadaDataResponse;
import com.cloud.component.fadada.response.FadadaResultResponse;
import com.cloud.component.properties.FadadaProperties;
import com.fadada.sdk.base.client.FddBaseClient;
import com.fadada.sdk.base.model.req.*;
import com.fadada.sdk.verify.client.FddVerifyClient;
import com.fadada.sdk.verify.model.req.ApplyCertParams;
import com.fadada.sdk.verify.model.req.CompanyVerifyUrlParams;
import com.fadada.sdk.verify.model.req.PersonVerifyUrlParams;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Objects;

/**
 * 法大大接口工具
 *
 * @author mft
 */
@Slf4j
public class FadadaClient {

    private static final String V = "2.0";

    private final FadadaProperties fadadaProperties;

    public FadadaClient(FadadaProperties fadadaProperties) {
        this.fadadaProperties = fadadaProperties;
    }

    /**
     * 1. 注册账号
     *
     * @param openId openId
     * @param type   账号类型 1、个人 2、企业
     * @return 法大大返回参数
     */
    public FadadaDataResponse accountRegister(String openId, String type) {
        FddBaseClient baseClient = new FddBaseClient(fadadaProperties.getAddId(), fadadaProperties.getAppKey(), V, fadadaProperties.getHost());
        RegisterAccountParams params = new RegisterAccountParams();
        // 账号类型 1、个人 2、企业
        params.setAccountType(type);
        // 平台方自定义唯一标识
        params.setOpenId(openId);
        String result = baseClient.invokeRegisterAccount(params);
        log.info("法大大返回参数：{}", result);
        return JsonUtil.toPojo(JsonUtil.toMap(result), FadadaDataResponse.class);
    }

    /**
     * 2. 获取企业实名认证地址
     *
     * @return 法大大返回信息
     */
    public FadadaCompanyUrlResponse getCompanyVerifyUrl(CompanyVerifyRequest companyVerifyRequest) {
        CompanyVerifyUrlParams params = new CompanyVerifyUrlParams();
        params.setCustomerId(companyVerifyRequest.getCustomerId());
        // 是否允许用户页面修改 1允许 2不允许 默认为1
        params.setPageModify(companyVerifyRequest.getPageModify());
        // 以下是非必填参数
        // 实名认证套餐类型： 0：标准方案（对公打款+纸质审核+法人身份+法 人授权）；1：对公打款；2：纸质审核；3：法人身份（授权）认证
        params.setVerifiedWay(companyVerifyRequest.getVerifiedWay());
        //管理员认证套餐类型： 0：三要素标准方案； 1：三要素补充方案； 2：四要素标准方案； 3：四要素补充方案； 4：纯三要素方案； 5：纯四要素方案；
        params.setMVerifiedWay(companyVerifyRequest.getMVerifiedWay());
        //指定管理员为"法人"身份下，允许的认证方式∶ 1.法人身份认证; 2.对公打款认证; 3.纸质材料认证;
        params.setLegalAllowCompanyVerifyWay(companyVerifyRequest.getLegalAllowCompanyVerifyWay());
        //指定管理员为"代理人"身份下，允许的认证方式∶ 1.法人授权认证; 2.对公打款认证; 3.纸质材料认证;
        // 可同时传入多个值，如"1,2“，表示代理身份下，只允 许"法人授权认证"及"对公打款认证"方式，页面隐藏纸质材料认证"方式;传入空值时，表示允许所有认证方式;
        params.setAgentAllowCompanyVerifyWay(companyVerifyRequest.getAgentAllowCompanyVerifyWay());
        //0-只需要头像面 1-头像面与国徽面都需要 2-都不需要 默认为0
        params.setIdPhotoOptional(companyVerifyRequest.getIdPhotoOptional());
        // 公司信息
        CompanyVerifyUrlParams.CompanyInfo companyInfo = new CompanyVerifyUrlParams.CompanyInfo();
        if (Objects.nonNull(companyVerifyRequest.getCompanyInfo())) {
            companyInfo.setCompanyName(companyVerifyRequest.getCompanyInfo().getCompanyName());
            companyInfo.setCreditNo(companyVerifyRequest.getCompanyInfo().getCreditNo());
            companyInfo.setCreditImagePath(companyVerifyRequest.getCompanyInfo().getCreditImagePath());
        }
        params.setCompanyInfo(companyInfo);
        //1.法人，2.代理人
        params.setCompanyPrincipalType(companyVerifyRequest.getCompanyPrincipalType());
        // 法人信息
        CompanyVerifyUrlParams.LegalInfo legalInfo = new CompanyVerifyUrlParams.LegalInfo();
        if (Objects.nonNull(companyVerifyRequest.getLegalInfo())) {
            legalInfo.setLegalId(companyVerifyRequest.getLegalInfo().getLegalId());
            legalInfo.setLegalMobile(companyVerifyRequest.getLegalInfo().getLegalMobile());
            legalInfo.setLegalIdFrontPath(companyVerifyRequest.getLegalInfo().getLegalIdFrontPath());
            legalInfo.setBankCardNo(companyVerifyRequest.getLegalInfo().getBankCardNo());
        }
        legalInfo.setLegalName(companyVerifyRequest.getLegalName());
        params.setLegalInfo(legalInfo);
        params.setLegalIdFrontImg(companyVerifyRequest.getLegalIdFrontImg());
        //代理人信息
        CompanyVerifyUrlParams.AgentInfo agentInfo = new CompanyVerifyUrlParams.AgentInfo();
        if (Objects.nonNull(companyVerifyRequest.getAgentInfo())) {
            agentInfo.setAgentName(companyVerifyRequest.getAgentInfo().getAgentName());
            agentInfo.setAgentId(companyVerifyRequest.getAgentInfo().getAgentId());
            agentInfo.setAgentMobile(companyVerifyRequest.getAgentInfo().getAgentMobile());
            agentInfo.setAgentIdFrontPath(companyVerifyRequest.getAgentInfo().getAgentIdFrontPath());
            agentInfo.setBankCardNo(companyVerifyRequest.getAgentInfo().getBankCardNo());
        }
        params.setAgentInfo(agentInfo);
        params.setAgentIdFrontImg(companyVerifyRequest.getAgentIdFrontImg());
        params.setCreditImage(companyVerifyRequest.getCreditImage());
        //银行卡信息
        CompanyVerifyUrlParams.BankInfo bankInfo = new CompanyVerifyUrlParams.BankInfo();
        if (Objects.nonNull(companyVerifyRequest.getBankInfo())) {
            bankInfo.setBankName(companyVerifyRequest.getBankInfo().getBankName());
            bankInfo.setBankId(companyVerifyRequest.getBankInfo().getBankId());
            bankInfo.setSubbranchName(companyVerifyRequest.getBankInfo().getSubbranchName());
        }
        params.setBankInfo(bankInfo);
        params.setBankProvinceName(companyVerifyRequest.getBankProvinceName());
        params.setBankCityName(companyVerifyRequest.getBankCityName());
        //是否认证成功后自动申请实名证书 参数值为“0”：不申请，参数值 为“1”：自动申请
        params.setCertFlag(companyVerifyRequest.getCertFlag());
        //add（新增）modify（修改）不传默认add
        params.setOption(companyVerifyRequest.getOption());
        //管理员认证流水号
        params.setVerifiedSerialNo(companyVerifyRequest.getVerifiedSerialno());
        //企业注册申请表
        params.setAuthorizationFile(companyVerifyRequest.getAuthorizationFile());
        //法人姓名（代理人认证想要传法人姓名可用此参数）
        params.setLegalName(companyVerifyRequest.getLegalName());
        //法人/代理人/个人手机号
        params.setMobile(companyVerifyRequest.getMobile());
        //法人授权手机号,当接口中传入了“法人授权手机号”字段 时，页面中选择法人授权认证时，会将传入的手机号展示出来
        params.setLegalAuthorizedMobile(companyVerifyRequest.getLegalAuthorizedMobile());
        //zh:中文，en:英文默认：中文
        params.setLang(companyVerifyRequest.getLang());
        //0：企业；1：政府/事业单位；2：其他组织；3：个体工商户
        params.setOrganization_type(companyVerifyRequest.getOrganizationType());
        params.setReturnUrl(companyVerifyRequest.getReturnUrl());
        params.setNotifyUrl(companyVerifyRequest.getNotifyUrl());
        String result = client().invokeCompanyVerifyUrl(params);
        log.info("法大大返回参数：{}", result);
        return JsonUtil.toPojo(JsonUtil.toMap(result), FadadaCompanyUrlResponse.class);
    }

    /**
     * 3. 获取个人实名认证地址
     */
    public FadadaDataResponse getPersonVerifyUrl() {
        PersonVerifyUrlParams params = new PersonVerifyUrlParams();
        //客户编号
        params.setCustomerId("填入注册个人账户时返回的客户编号");
        //实名认证套餐类型
        params.setVerifiedWay("0");
        //是否允许用户页面修改 1允许 2不允许 //以下是非必填参数
        params.setPageModify("1");
        params.setNotifyUrl("");
        //异步回调地址
        params.setReturnUrl("");
        //同步通知url
        params.setCustomerName("");
        //姓名
        params.setCustomerIdentType("");
        //证件类型
        params.setCustomerIdentNo("");
        //证件号码
        params.setMobile("");
        //手机号码
        params.setIdentFrontPath("");
        //证件正面照下载地址
        params.setIdentBackPath("");
        //证件反面照下载地址
        params.setResultType("");
        //刷脸是否显示结果页面
        params.setCertFlag("");
        //是否认证成功后自动申请实名证书
        params.setCertType("");
        //证件类型
        params.setBankCardNo("");
        //个人银行卡
        params.setOption("");
        //不传默认add
        params.setIdPhotoOptional("");
        //是否需要上传身份照片
        params.setIsMinProgram("");
        //是否跳转法大大公证处小程序认证
        params.setLang("");
        //zh：中文；en：英文
        params.setIsAllowOverseasBankCardAuth("");
        //海外用户是否支持银行卡认证
        params.setIdentFrontImg(new File(""));
        //证件正面照图片文件
        params.setIdentBackImg(new File(""));
        //证件反面照图片文件
        String result = client().invokePersonVerifyUrl(params);
        log.info("法大大返回参数：{}", result);
        return JsonUtil.toPojo(JsonUtil.toMap(result), FadadaDataResponse.class);
    }

    /**
     * 4. 实名证书申请
     */
    public FadadaDataResponse applyCert() {
        ApplyCertParams params = new ApplyCertParams();
        //客户编号
        params.setCustomerId("注册个人账号时返回");
        //填写获取实名认证地址返回的交易号transactionNo
        params.setVerifiedSerialNo("");
        String result = client().invokeApplyCert(params);
        log.info("法大大返回参数：{}", result);
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
        String result = baseClient().invokeAddSignature(params);
        log.info("法大大返回参数：{}", result);
        return JsonUtil.toPojo(JsonUtil.toMap(result), FadadaDataResponse.class);
    }

    /**
     * 6. 自定义印章
     */
    public FadadaDataResponse customSignature(String customerId, String content) {
        CustomSignatureParams params = new CustomSignatureParams();
        //印章展示的文字
        params.setContent(content);
        //客户编号
        params.setCustomerId(customerId);
        String result = baseClient().invokeCustomSignature(params);
        log.info("法大大返回参数：{}", result);
        return JsonUtil.toPojo(JsonUtil.toMap(result), FadadaDataResponse.class);
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
    public FadadaResultResponse uploaddocs() {
        UploadDocsParams params = new UploadDocsParams();
        params.setContractId("UUID1231254135136");
        //自定义合同id
        params.setDocTitle("授权代理协议范本");
        //合同标题
        // PDF文档和下载地址二选一
        params.setFile(new File("D:\\授权协议.pdf"));
        //PDF文档
        params.setDocUrl("");
        //合同公网下载地址
        params.setDocType(".pdf");
        //合同类型 目前仅支持pdf格式
        String result = baseClient().invokeUploadDocs(params);
        log.info("法大大合同上传返回参数：{}", result);
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
        String result = baseClient().invokeUploadTemplate(params);
        log.info("法大大返回参数：{}", result);
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

    /**
     * 法大大请求参数
     */
    public FddVerifyClient client() {
        return new FddVerifyClient(fadadaProperties.getAddId(), fadadaProperties.getAppKey(), V, fadadaProperties.getHost());
    }

    /**
     * 法大大请求参数
     */
    public FddBaseClient baseClient() {
        return new FddBaseClient(fadadaProperties.getAddId(), fadadaProperties.getAppKey(), V, fadadaProperties.getHost());
    }

}
