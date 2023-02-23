package com.cloud.component.fadada.response;

import lombok.Data;

/**
 * 响应url
 *
 * @author mft
 */
@Data
public class FadadaCompanyCertCompanyResponse {

    public static final String VERIFY_TYPE_PAY = "0";

    /**
     * 关联法人/代理人交易号
     */
    private String relatedTransactionNo;

    /**
     * 认证方式：
     * 0，银行卡认证；
     * 1，纸质审核认证
     * 2，法人认证
     * 4，法人授权认证
     */
    private String verifyType;

    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 统一社会信用代码
     */
    private String organization;

    /**
     * 法人姓名
     */
    private String legalName;

    /**
     * 法人身份证号
     */
    private String legal;

    /**
     * 企业认证申请表
     */
    private String regFormPath;

    /**
     * 0：未认证；
     * 1：管理员资料已提交；
     * 2：企业基本资料(没有申请表)已提交；
     * 3：已提交待审核；
     * 4：审核通过（认证完成）；
     * 5：审核不通过；
     * 6：人工初审通过（认证未完成，还需按提示完成接下来的操作）
     */
    private String status;

    /**
     * 不通过原因
     */
    private String auditFailReason;

    /**
     * 审核时间
     */
    private String auditorTime;

    /**
     * 1：代理人
     * 0：法人
     */
    private String hasagent;

    /**
     * 0：企业；
     * 1：政府/事业单位；
     * 2：其他组织；
     * 3：个体工商户
     */
    private String organizationType;

    /**
     * 证件类型，
     * 0，统一社会信用代码；1，普通营业执照；
     */
    private String certificatesType;

    /**
     * 法人授权,法人手机号
     * 法人授权认证才会有值
     */
    private String legalMobile;

}
