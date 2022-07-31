package com.cloud.component.fadada.request;

import lombok.Data;

/**
 * 银行账号信息
 *
 * @author mft
 */
@Data
public class BankInfoRequest {

    /**
     * 银行名称
     * <p> 非必填
     */
    private String bankName;

    /**
     * 银行帐号
     * <p> 非必填
     */
    private String bankId;

    /**
     * 开户支行名称
     * <p> 非必填
     */
    private String subbranchName;

}
