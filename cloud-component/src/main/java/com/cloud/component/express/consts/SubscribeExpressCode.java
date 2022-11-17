package com.cloud.component.express.consts;

import com.cloud.common.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订阅状态
 * @author nlsm
 */
@Getter
@AllArgsConstructor
public enum SubscribeExpressCode {

    /**
     * 监控中
     */
    SUBSCRIPTION_SUCCESS("200", "订阅成功"),

    SUBSCRIPTION_FAIL("500", "订阅失败"),
    REPEAT_SUBSCRIPTION("501", "重复订阅");

    private final String code;

    private final String msg;

    public static SubscribeExpressCode fromCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            for (SubscribeExpressCode value : SubscribeExpressCode.values()) {
                if (value.getCode().equals(code)) {
                    return value;
                }
            }
        }
        return SUBSCRIPTION_FAIL;
    }
}
