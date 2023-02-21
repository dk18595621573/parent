package com.cloud.component.fadada.response;

import lombok.Data;

/**
 * 响应url
 *
 * @author mft
 */
@Data
public class FadadaCompanyCertDataResponse extends FadadaCompanyResponse {

    /**
     * 交易号（需要保存，用于证书申请和实名认证查询）
     * 必填
     */
    private String transactionNo;

    /**
     * 1个人
     * 2.企业
     */
    private String type;

    /**
     * 企业信息
     */
    private FadadaCompanyCertCompanyResponse company;

    /**
     * 企业银行卡信息
     */
    private FadadaCompanyCertBankCardResponse bankCard;

}
