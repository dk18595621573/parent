package com.cloud.webmvc.properties;

import lombok.Data;

import java.io.Serializable;

/**
 * xss配置.
 *
 * @author zenghao
 * @date 2022/12/19
 */
@Data
public class XssProperties implements Serializable {

    public static final String ENABLED = "cloud.system.xss.enabled";

    private String[] excludes;

    private String[] urlPatterns;
}
