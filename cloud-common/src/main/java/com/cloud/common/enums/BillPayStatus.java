package com.cloud.common.enums;

/**
 * @author dk185
 * 结算支付状态
 */
public enum BillPayStatus {
    /** 未支付 */
    NO_PAY(1, "未支付"),
    /** 已支付 */
    UNCONFIRMED(2, "已支付"),
    /** 已确认 */
    CONFIRMED(3, "已确认");

    private final Integer code;
    private final String msg;

    BillPayStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static BillPayStatus fromCode(Integer code) {
        for (BillPayStatus type : BillPayStatus.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return NO_PAY;
    }
}
