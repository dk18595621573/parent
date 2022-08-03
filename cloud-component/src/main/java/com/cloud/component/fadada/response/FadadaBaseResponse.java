package com.cloud.component.fadada.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 法大大基本响应体
 *
 * @author mft
 */
@Data
public class FadadaBaseResponse implements Serializable {

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

}
