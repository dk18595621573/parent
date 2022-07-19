package com.cloud.component.chinapay.exception;

/**
 * 银联接口异常.
 *
 * @author zenghao
 * @date 2022/6/20
 */
public class ChinaPayException extends RuntimeException {

    /**
     * 应答状态
     * 必填
     */
    private String respCode;

    public ChinaPayException(final String message) {
        super(message);
    }

    public ChinaPayException(final String respCode, final String message) {
        super(message);
        this.respCode = respCode;
    }

    public String getRespCode() {
        return respCode;
    }
}
