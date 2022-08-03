package com.cloud.component.fadada.request;

import lombok.Data;

/**
 * 法大大签署回调参数
 *
 * @author mft
 */
@Data
public class ExtsignCallbackRequest {

    /**
     * 交易号
     * <p> 必填
     */
    private String transactionId;

    /**
     * 合同编号
     * <p> 必填
     */
    private String contractId;

    /**
     * 签章结果代码
     * 3000（签章成功）
     * 3001（签章失败）
     * <p> 必填
     */
    private String resultCode;

    /**
     * 签章结果描述
     * <p> 必填
     */
    private String resultDesc;

    /**
     * 下载地址
     * <p> 必填
     */
    private String downloadUrl;

    /**
     * 查看地址
     * <p> 必填
     */
    private String viewpdfUrl;

    /**
     * 加密后的摘要
     * <p> 必填
     */
    private String msgDigest;


}
