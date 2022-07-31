package com.cloud.component.fadada.response;

import lombok.Data;

/**
 * 响应url
 *
 * @author mft
 */
@Data
public class FadadaDataSignatureResponse {

    /**
     * 签章图片ID
     * 可选
     */
    private String signatureId;

    /**
     * 扩展信息，目前为空
     * 可选
     */
    private String signatureSubInfo;
}
