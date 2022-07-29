package com.cloud.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 批量导出 类型.
 *
 * @author zenghao
 * @date 2022/7/29
 */
@Getter
@AllArgsConstructor
public enum ExportType {

    ALL_ORDER(1);

    private final int code;
}
