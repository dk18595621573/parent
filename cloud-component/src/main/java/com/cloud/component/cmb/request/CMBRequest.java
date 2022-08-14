package com.cloud.component.cmb.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求头参数
 */
@Data
public class CMBRequest<T> implements Serializable {
    /**
     * 请求头参数
     */
    private HeadRequest head;
    /**
     * 请求体参数
     */
    private T body;

    /**
     * 请求头参数
     */
    @Data
    public static class HeadRequest {
        private String funcode;
        private String userid;
        private String reqid;
    }
}
