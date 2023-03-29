package com.cloud.idempotent.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * 幂等结果响应.
 *
 * @author zenghao
 * @date 2023/2/27
 */
@Data
public class IdempotentResult<T> implements Serializable {

    /**
     * 接口响应是否成功
     */
    private Boolean success;

    /**
     * 接口响应结果
     */
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
    private T data;

    public IdempotentResult() {}

    private IdempotentResult(final Boolean success, final T data) {
        this.success = success;
        this.data = data;
    }

    public static <T> IdempotentResult<T> success(final T data) {
        return new IdempotentResult<>(true, data);
    }

    public static <T> IdempotentResult<T> error() {
        return new IdempotentResult<>(false, null);
    }
}
