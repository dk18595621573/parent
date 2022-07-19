package com.cloud.component.express.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 错误码.
 *
 * @author zenghao
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

    /**
     * 物流API异常.
     */
    API_ERROR(204301, "物流API异常"),
    /**
     * 快递公司错误（不扣次数）.
     */
    COMPANY_ERROR(204301, "快递公司错误"),
    /**
     * 运单号错误（不扣次数）.
     */
    EXPRESS_ERROR(204302, "运单号错误"),
    /**
     * 查询失败（不扣次数）.
     */
    QUERY_ERROR(204303, "查询失败"),
    /**
     * 查不到物流信息（扣次数）
     */
    NOT_FOUND(204304, "查不到物流信息"),
    /**
     * 寄件人或收件人手机尾号错误（不扣次数）
     */
    PARAM_ERROR(204305, "寄件人或收件人手机尾号错误"),
    ;

    private final int code;

    private final String msg;

    public static ErrorCode fromCode(Integer code) {
        if (Objects.nonNull(code)) {
            for (ErrorCode value : ErrorCode.values()) {
                if (value.getCode() == code) {
                    return value;
                }
            }
        }
        return API_ERROR;
    }
}