package com.cloud.component.honor.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 快递公司枚举.
 *
 * @author Luo
 * @date 2023-03-07 9:13
 */
@Getter
@AllArgsConstructor
public enum HonorExpressEnum {

    /**
     * 顺丰速运.
     */
    CODE_1("SF", "shunfeng", "顺丰速运"),

    ;

    /**
     * 荣耀code.
     */
    private final String honorCode;

    /**
     * code.
     */
    private final String code;

    /**
     * 说明.
     */
    private final String explain;

    /**
     * 根据荣耀code获取.
     *
     * @param code code
     * @return 结果
     */
    public static HonorExpressEnum getByHonorCode(final String code) {
        return Arrays.stream(HonorExpressEnum.values()).filter(i -> i.getHonorCode().equals(code)).findFirst().orElse(null);
    }

}
