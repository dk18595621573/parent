package com.cloud.component.fadada.response;

import lombok.Data;

/**
 * 响应url
 *
 * @author mft
 */
@Data
public class FadadaCompanyUrlResponse extends FadadaCompanyResponse {

    /**
     * 交易号（需要保存，用于证书申请和实名认证查询）
     * 必填
     */
    private String transactionNo;

    /**
     * 地址（需要保存，遇到中途退出认证或页面过期等情况可重新访问）
     * 必填
     */
    private String url;
}
