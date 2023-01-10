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
    private String transaction_id;

    /**
     * 合同编号
     * <p> 必填
     */
    private String contract_id;

    /**
     * 签章结果代码
     * 3000（签章成功）
     * 3001（签章失败）
     * <p> 必填
     */
    private String result_code;

    /**
     * 签章结果描述
     * <p> 必填
     */
    private String result_desc;

    /**
     * 下载地址
     * <p> 必填
     */
    private String download_url;

    /**
     * 查看地址
     * <p> 必填
     */
    private String viewpdf_url;

    /**
     * 加密后的摘要
     * <p> 必填
     */
    private String msg_digest;


}
