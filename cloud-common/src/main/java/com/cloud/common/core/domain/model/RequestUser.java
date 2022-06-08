package com.cloud.common.core.domain.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 本次请求用户信息.
 *
 * @author zenghao
 * @date 2022/6/6
 */
@Data
public class RequestUser implements Serializable {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

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

    /**
     * 权限列表
     */
    private Set<String> permissions;
}
