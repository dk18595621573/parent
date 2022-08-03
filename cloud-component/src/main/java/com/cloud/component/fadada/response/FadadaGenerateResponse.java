package com.cloud.component.fadada.response;

import lombok.Data;

/**
 * 响应url
 *
 * @author mft
 */
@Data
public class FadadaGenerateResponse extends FadadaResultResponse {

    /**
     * 合同下载地址（填充成功时有值，填充失败无值）
     * 非必填
     */
    private String downloadUrl;

    /**
     * 合同查看地址（填充成功时有值，填充失败无值）
     * 非必填
     */
    private String viewpdfUrl;
}
