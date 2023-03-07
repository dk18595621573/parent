package com.cloud.component.cmb.exception;

/**
 * 招商银行异常.
 *
 * @author nlsm
 */
public class CmbException extends RuntimeException {

    private static final long serialVersionUID = -397441706693761861L;

    /**
     * 应答状态
     */
    private String respCode;

    public CmbException(final String message) {
        super(message);
    }

    public CmbException(final String respCode, final String message) {
        super(message);
        this.respCode = respCode;
    }

    public String getRespCode() {
        return respCode;
    }

}
