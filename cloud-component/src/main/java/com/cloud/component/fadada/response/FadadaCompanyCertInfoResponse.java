package com.cloud.component.fadada.response;

import lombok.Data;

/**
 * 响应url
 *
 * @author mft
 */
@Data
public class FadadaCompanyCertInfoResponse {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回信息
     * 非必填
     */
    private FadadaCompanyCertDataResponse data;

}
