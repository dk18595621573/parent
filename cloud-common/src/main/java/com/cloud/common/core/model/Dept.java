package com.cloud.common.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 部门信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept implements Serializable {
    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;

}