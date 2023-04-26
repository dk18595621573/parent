package com.cloud.common.enums;

import com.cloud.common.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 订单归属类型.
 *
 * @author nlsm
 * @date 2023-3-21 19:05:21
 */
@Getter
@AllArgsConstructor
public enum BelongType {

    /**
     * 默认.
     */
    DEFAULT("DEFAULT", "默认内部系统"),

    /**
     * 华盛.
     */
    HUASHENG("HUASHENG", "华盛"),

    /**
     * 广移.
     */
    GUANGYI("GUANGYI", "广移"),

    /**
     * 运营商.
     */
    OPERATOR("OPERATOR", "运营商"),

    /**
     * 其它.
     */
    OTHERS("OTHERS", "其他");

    /**
     * code.
     */
    private final String code;

    /**
     * message.
     */
    private final String message;

    /**
     * 根据code获取.
     *
     * @param code code
     * @return 结果
     */
    public static BelongType fromCode(final String code) {
        return Arrays.stream(BelongType.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
    }

    /**
     * 是否为外部订单.
     *
     * @param code code
     * @return true：是、false：否
     */
    public static boolean isExternal(final String code) {
        if (StringUtils.isBlank(code)) {
            return false;
        }
        return Arrays.asList(HUASHENG.getCode(), GUANGYI.getCode(), OPERATOR.getCode()).contains(code);
    }

}
