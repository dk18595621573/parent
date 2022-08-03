package com.cloud.component.fadada.response;

import lombok.Data;

/**
 * 响应信息
 *
 * @author mft
 */
@Data
public class FadadaAddSignatureResponse extends FadadaSignatureDataResponse {

    /**
     * 印章id
     * 必填
     */
    private String signature_id;

    /**
     * 印章图片base64
     * 必填
     */
    private String signature_img_base64;
}
