package com.cloud.webmvc.properties;

import lombok.Data;

import java.io.Serializable;

@Data
public class SecurityProperties implements Serializable {

    /**
     * 允许匿名访问的接口地址.
     */
    private String[] allows;

}