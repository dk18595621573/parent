package com.cloud.component.express.consts;

/**
 * 拦截类型
 * @author nlsm
 */
public enum InterceptType {
    /** 转寄 */
    SEND_ON("1", "转寄"),
    /** 退回 */
    RETURN("2", "退回"),
    /** 优派 */
    View_Sonic("3", "优派"),
    /** 再派 */
    AGAIN_SONIC("4", "再派"),
    /** 改自取（改派-其他自取点取件） */
    ALTER_INVITE("5", "改自取"),
    /** 改派送（上门派送） */
    ALTER_DELIVERY("6", "改派送"),
    /** 更改派送时间 */
    ALTER_DELIVERY_TIME("7", "更改派送时间"),
    /** 修改收件人信息 */
    ALTER_RECIPIENTS("8", "修改收件人信息"),
    /** 更改付款方式 */
    ALTER_PAY("9", "更改付款方式"),
    /** 修改代收货款 */
    ALTER_PAYMENT_GOODS("10", "修改代收货款"),
    /** 作废 */
    CANCELLATION("12", "作废"),

    ;

    private final String code;
    private final String msg;

    InterceptType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
