package com.cloud.webmvc.excel.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 合并坐标行范围.
 *
 * @author zenghao
 * @date 2021/12/26
 */
@Data
public class RowRange implements Serializable {

    /**
     * 起始位置.
     */
    private Integer start;

    /**
     * 结束位置.
     */
    private Integer end;

    public RowRange(final int start, final int end) {
        this.start = start;
        this.end = end;
    }
}
