package com.cloud.idempotent.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 幂等结果响应.
 *
 * @author zenghao
 * @date 2023/2/27
 */
@Data
public class IdempotentResult implements Serializable {

    /**
     * 接口响应是否成功
     */
    private Boolean success;

    /**
     * 接口响应结果
     */
    private Object data;

    public IdempotentResult() {}

    private IdempotentResult(final Boolean success, final Object data) {
        this.success = success;
        this.data = data;
    }

    public static IdempotentResult success(final Object data) {
        return new IdempotentResult(true, data);
    }

    public static IdempotentResult error() {
        return new IdempotentResult(false, null);
    }
}
