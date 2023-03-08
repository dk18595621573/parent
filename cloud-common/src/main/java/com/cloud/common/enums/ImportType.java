package com.cloud.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 批量导入 类型.
 *
 * @author zhouyong
 * @date 2023/03/07
 */
@Getter
@AllArgsConstructor
public enum ImportType {

    /**
     * 华盛订单导入
     */
    HS_ORDER(1);

    private final int code;
}
