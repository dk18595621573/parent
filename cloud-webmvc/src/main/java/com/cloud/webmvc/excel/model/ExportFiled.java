package com.cloud.webmvc.excel.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 最终需要导出的字段.
 *
 * @author zenghao
 * @date 2022/7/29
 */
@Data
@AllArgsConstructor
public class ExportFiled implements Serializable {

    private static final long serialVersionUID = 6538056393491916800L;

    /**
     * 字段名.
     */
    private String fieldName;

    /**
     * 是否需要合并.
     */
    private Boolean merged;
}
