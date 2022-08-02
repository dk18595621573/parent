package com.cloud.component.fadada.request;

import lombok.Data;

/**
 * 法大大认证回调参数
 *
 * @author mft
 */
@Data
public class VerifyCallbackRequest {

    /**
     * 平台方id
     * <p> 必填
     */
    private String appId;

    /**
     * 认证序列号
     * <p> 必填
     */
    private String serialNo;

    /**
     * 客户编号
     * <p> 必填
     */
    private String customerId;

    /**
     * 认证状态
     * 0：未认证；1：管理员资料已提交；2：企业基本资料(没有申请表)已提交；3：已提交待审核；4：审核通过（认证完成）；
     * 5：审核不通过；6：人工初审通过（认证未完成，还需按提示完成接下来的操作）
     * <p> 必填
     */
    private String status;

    /**
     * 不通过原因描述
     * <p> 必填
     */
    private String statusDesc;

    /**
     * 0：没有申请证书或者申请证书失败，
     * 1：成功申请证书
     * <p> 必填
     */
    private String certStatus;


}
