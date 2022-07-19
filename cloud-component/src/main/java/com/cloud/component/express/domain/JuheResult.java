package com.cloud.component.express.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: duk
 * @Date: 2022-05-19 11:34
 * @Description: 聚合接口返回数据.
 */
@Data
public class JuheResult<T> implements Serializable {
    /**
     * 聚合数据成功.
     */
    public static final int SUCCESS_CODE = 0;

    /**
     * 错误码，0表示查询正常，其他表示查询不到物流信息或发生了其他错误.
     */
    @JsonProperty("error_code")
    private Integer errorCode;

    /**
     * 原因.
     */
    private String reason;

    private T result;
}
