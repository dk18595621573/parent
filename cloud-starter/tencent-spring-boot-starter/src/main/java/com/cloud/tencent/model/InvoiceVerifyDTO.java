package com.cloud.tencent.model;

import lombok.Data;

/**
 * @author dk185
 * 发票核验返回参数
 */
@Data
public class InvoiceVerifyDTO {
    /** 异常类型 */
    private String errorCode;
    /** 异常说明 */
    private String msg;
    /** 是否是真实发票 */
    private Boolean success;

    /** 单次请求唯一值 */
    private String requestId;

    /**
     * 成功
     * @return 成功对象
     */
    public static InvoiceVerifyDTO success(String requestId){
        return new InvoiceVerifyDTO("", "", true, requestId);
    }
    /**
     * 失败
     * @return 成功对象
     */
    public static InvoiceVerifyDTO error(String errorCode, String msg, String requestId){
        return new InvoiceVerifyDTO(errorCode, msg, false, requestId);
    }


    private InvoiceVerifyDTO(){}
    private InvoiceVerifyDTO(String errorCode, String msg, Boolean success, String requestId) {
        this.errorCode = errorCode;
        this.msg = msg;
        this.success = success;
        this.requestId = requestId;
    }
}
