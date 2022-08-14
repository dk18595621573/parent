package com.cloud.component.fadada.response;

import lombok.Data;

/**
 * 响应url
 *
 * @author mft
 */
@Data
public class FadadaGenerateResponse {

    /**
     * 状态码
     * 必填
     */
    private String code;

    /**
     * 状态描述
     * 必填
     */
    private String msg;

    /**
     * 处理结果(success：成功 error：失败)
     * 必填
     */
    private String result;

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
