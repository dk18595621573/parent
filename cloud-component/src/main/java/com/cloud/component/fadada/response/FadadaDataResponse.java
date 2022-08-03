package com.cloud.component.fadada.response;

import lombok.Data;

/**
 * 响应信息
 *
 * @author mft
 */
@Data
public class FadadaDataResponse extends FadadaBaseResponse {

    /**
     * 返回信息
     * 非必填
     */
    private String data;
}
