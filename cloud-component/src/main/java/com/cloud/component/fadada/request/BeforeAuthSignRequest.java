package com.cloud.component.fadada.request;

import lombok.Data;

import java.io.File;

/**
 * 授权自动签
 * @author peijiawei
 * @date 2/7/23 9:05 AM
 */
@Data
public class BeforeAuthSignRequest {

    /**
     * 交易号
     * <p> 必填
     */
    private String transactionId;

    /**
     * 1:授权自动签（目前只能填1）
     * <p> 必填
     */
    private String authType;

    /**
     * 合同ID
     * <p> 必填
     */
    private String contractId;

    /**
     * 客户编号
     * <p> 必填
     */
    private String customerId;

    /**
     * 同步通知签署结果地址
     * <p> 非必填
     */
    private String returnUrl;

    /**
     * 异步通知签署结果地址
     * <p> 非必填
     */
    private String notifyUrl;

    /**
     * 授权页面返回地址
     */
    private String respReturnUrl;
}
