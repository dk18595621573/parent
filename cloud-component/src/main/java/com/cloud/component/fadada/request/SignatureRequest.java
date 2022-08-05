package com.cloud.component.fadada.request;

import lombok.Data;

import java.io.File;

/**
 * 签章图片信息
 *
 * @author mft
 */
@Data
public class SignatureRequest {

    /**
     * 客户编号
     * <p> 必填
     */
    private String customerId;

    /**
     * 签章图片base64
     * <p> 必填
     */
    private String signatureImgBase64;

    /**
     * 签章图片地址
     * <p> 必填
     */
    private File file;

    /**
     * 签章图片公网地址
     * <p> 非必填
     */
    private String imgUrl;

}
