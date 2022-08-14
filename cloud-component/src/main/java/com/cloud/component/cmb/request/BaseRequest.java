package com.cloud.component.cmb.request;


import lombok.Data;

import java.io.Serializable;

/**
 * 基本请求体.
 * @param
 */
@Data
public class BaseRequest<T> implements Serializable {

    /**
     * 请求方法名
     */
    private String funcode;

    /**
     * 请求体参数
     */
    private T body;

}
