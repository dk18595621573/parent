package com.cloud.component.chinapay.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 基本响应内容.
 *
 * @author zenghao
 * @date 2022/6/20
 */
@Data
public class BaseResponse implements Serializable {

    public static final String SUCCESS_CODE = "00000000";

    public static final String SUB_BANK_CODE = "80010150";

    /**
     * 应答状态
     * 必填
     */
    private String respCode;

    /**
     * 返回详细的操作 结果信息
     * 非必填
     */
    private String respMsg;
}
