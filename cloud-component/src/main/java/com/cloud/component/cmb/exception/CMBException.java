package com.cloud.component.cmb.exception;

/**
 * 招商银行异常
 */
public class CMBException extends RuntimeException {

    /**
     * 应答状态
     */
    private String respCode;

    public CMBException(final String message) {
        super(message);
    }

    public CMBException(final String respCode, final String message) {
        super(message);
        this.respCode = respCode;
    }

    public String getRespCode() {
        return respCode;
    }
}
