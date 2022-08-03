package com.cloud.component.fadada.request;

import lombok.Data;

import java.io.File;

/**
 * 获取企业实名认证地址
 *
 * @author mft
 */
@Data
public class CompanyVerifyRequest {

    /**
     * 客户编号
     * 注册账号时返回
     * <p> 必填
     */
    private String customerId;

    /**
     * 实名认证套餐
     * 类型： 0：标准方案 （对公打款+纸质审核+法人身份+法人授 权）； 1：对公打款； 2：纸质审核； 3：法人身份（授权）认证 (管理员为法人时，只可选法人身份认证；管理员为代理人时，只可选法人授权认证; 选择了此认证方案后，组织类型只能选择企业或者个体工商户)
     * <p> 非必填
     */
    private String verifiedWay;

    /**
     * 管理员认证套餐类型： 0：三要素标准方案； 1：三要素补充方案； 2：四要素标准方案； 3：四要素补充方案； 4：纯三要素方案；5：纯四要素方案；
     * <p> 非必填
     */
    private String mVerifiedWay;

    /**
     * 指定管理员为"法人"身份下，允许的认证方式∶1.法人身份认证; 2.对公打款认证; 3.纸质材料认证;
     * <p> 非必填
     */
    private String legalAllowCompanyVerifyWay;

    /**
     * 指定管理员 为"代理人"身份下，允许的认证方式∶1.法人授权认证; 2.对公打款认证; 3.纸质材料认证;
     * <p> 非必填
     */
    private String agentAllowCompanyVerifyWay;

    /**
     * 是否需要上传身份照片
     * 0-只需要头像面 1-头像面与国徽面都需要 2-都不需要默认为0
     * <p> 非必填
     */
    private String idPhotoOptional;

    /**
     * 是否允许用户页面修改
     * 1允许 2不允许 默认为1
     * <p> 必填
     */
    private String pageModify;

    /**
     * 企业信息
     * <p> 非必填
     */
    private CompanyInfoRequest companyInfo;

    /**
     * 企业负责人身份: 1. 法人 2. 代理人
     * <p> 非必填
     */
    private String companyPrincipalType;

    /**
     * 法人信息
     * <p> 非必填
     */
    private LegalInfoRequest legalInfo;

    /**
     * 代理人信息
     * <p> 非必填
     */
    private AgentInfoRequest agentInfo;

    /**
     * 对公账号信息
     * <p> 非必填
     */
    private BankInfoRequest bankInfo;

    /**
     * 代理人证件正面照
     * <p> 非必填
     */
    private File agentIdFrontImg;

    /**
     * 统一社会信用代码证件照
     * <p> 非必填
     */
    private File creditImage;

    /**
     * 法人证件正面照
     * <p> 非必填
     */
    private File legalIdFrontImg;

    /**
     * 银行所在省份
     * <p> 非必填
     */
    private String bankProvinceName;

    /**
     * 银行所在市
     * <p> 非必填
     */
    private String bankCityName;

    /**
     * 是否认证成功后自动申请实名证书参数值为“0”： 不申请 参数值为“1”： 自动申请
     * <p> 非必填
     */
    private String certFlag;

    /**
     * add（新增） modify（修 改）
     * <p> 非必填
     */
    private String option;

    /**
     * 管理员认证流水号
     * <p> 非必填
     */
    private String verifiedSerialno;

    /**
     * 企业注册申请表
     * <p> 非必填
     */
    private File authorizationFile;

    /**
     * 法人姓名（代理人认证想要传法人姓名可用此参数）
     * <p> 非必填
     */
    private String legalName;

    /**
     * 法人/代理人/个人手机号
     * <p> 非必填
     */
    private String mobile;

    /**
     * 法人授权手机号
     * <p> 非必填
     */
    private String legalAuthorizedMobile;

    /**
     * zh:中文，en:英文，默认：中文
     * <p> 非必填
     */
    private String lang;

    /**
     * 组织类型
     * <p> 非必填
     */
    private String organizationType;

    /**
     * 回调地址
     * <p> 非必填
     */
    private String notifyUrl;

    /**
     * 同步通知url
     * <p> 非必填
     */
    private String returnUrl;

}
