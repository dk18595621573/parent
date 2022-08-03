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

    /**
     * 成功
     * @return 成功对象
     */
    public static InvoiceVerifyDTO success(){
        return new InvoiceVerifyDTO("", "", true);
    }
    /**
     * 失败
     * @return 成功对象
     */
    public static InvoiceVerifyDTO error(String errorCode, String msg){
        return new InvoiceVerifyDTO(errorCode, msg, false);
    }


    private InvoiceVerifyDTO(){}
    private InvoiceVerifyDTO(String errorCode, String msg, Boolean success) {
        this.errorCode = errorCode;
        this.msg = msg;
        this.success = success;
    }
}
