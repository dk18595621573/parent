package com.cloud.component.fadada.request;

import lombok.Data;

/**
 * 法人信息
 *
 * @author mft
 */
@Data
public class LegalInfoRequest {

    /**
     * 法人姓名
     * <p> 非必填
     */
    private String legalName;

    /**
     * 法人证件号
     * <p> 非必填
     */
    private String legalId;

    /**
     * 法人手机号
     * <p> 非必填
     */
    private String legalMobile;

    /**
     * 法人证件正面照下载地址
     * <p> 非必填
     */
    private String legalIdFrontPath;

    /**
     * 法人银行卡号
     * <p> 非必填
     */
    private String bankCardNo;

}
