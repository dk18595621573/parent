package com.cloud.common.core.domain.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求基本信息.
 *
 * @author zenghao
 * @date 2022/6/29
 */
@Data
public class BaseRequestInfo implements Serializable {

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;
}
