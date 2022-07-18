package com.cloud.common.core.message;

import lombok.Data;

import java.io.Serializable;

/**
 * demo 事件.
 *
 * @author zenghao
 * @date 2022/7/18
 */
@Data
public class Demo implements Serializable {

    private Integer integer;

    private String string;

    private double db;

    private boolean bl;

    private Boolean bool;
}
