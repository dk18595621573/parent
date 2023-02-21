package com.cloud.component.fadada.response;

import lombok.Data;

/**
 * 响应url
 *
 * @author mft
 */
@Data
public class FadadaCompanyCertBankCardResponse {

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 支行名称
     */
    private String bankDetailName;

    /**
     * 银行卡号
     */
    private String bankCardNo;

    /**
     * 联行号
     */
    private String branchBankCode;

    /**
     * 开户省
     */
    private String provinceName;

    /**
     * 开户市
     */
    private String cityName;

    /**
     * 打款状态
     * 0-未打款 1-已打款 2-打款中。
     */
    private String status;

    /**
     * 打款时间
     */
    private String enterTime;

}
