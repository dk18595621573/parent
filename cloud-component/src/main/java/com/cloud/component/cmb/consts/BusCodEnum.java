package com.cloud.component.cmb.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 业务代码枚举.
 *
 * @author Luo
 * @date 2023-03-07 9:13
 */
@Getter
@AllArgsConstructor
public enum BusCodEnum {

    /**
     * N01010：帐务查询.
     */
    N01010("N01010", "帐务查询"),

    /**
     * N02030：支付.
     */
    N02030("N02030", "支付");

    /**
     * code.
     */
    private final String code;

    /**
     * 说明.
     */
    private final String explain;

    /**
     * 根据code获取.
     *
     * @param code code
     * @return 结果
     */
    public static BusCodEnum getByCode(final String code) {
        return Arrays.stream(BusCodEnum.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
    }

}
