package com.cloud.component.fadada.execption;

import com.cloud.component.fadada.consts.FadadaRefusalCode;

/**
 * @author nlsm
 */
public class FadadaException extends RuntimeException {
    private final FadadaRefusalCode code;

    public FadadaException(final FadadaRefusalCode code) {
        super(code.getMsg());
        this.code = code;
    }

    public FadadaRefusalCode getCode() {
        return code;
    }
}
