package com.cloud.component.cmb.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 响应结果
 */
@Data
public abstract class BaseResponse implements Serializable {

    /**
     * 应答状态（true： 成功； false： 失败）
     * 必填
     */
    private Boolean success;

}
