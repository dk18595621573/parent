package com.cloud.common.core.domain.model;

import lombok.Data;

import java.util.Set;

/**
 * 本次请求用户信息.
 *
 * @author zenghao
 * @date 2022/6/6
 */
@Data
public class RequestUser extends BaseRequestInfo {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户账号
     */
    private String username;

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
     * 权限列表
     */
    private Set<String> permissions;

    /**
     * 角色列表
     */
    private Set<Role> roles;

    /**
     * 部门信息.
     */
    private Dept dept;

    public RequestUser toRequestUser() {
        RequestUser user = new RequestUser();
        user.setUserId(this.getUserId());
        user.setUsername(this.getUsername());
        user.setDeptId(this.getDeptId());
        user.setToken(this.getToken());
        user.setLoginTime(this.getLoginTime());
        user.setExpireTime(this.getExpireTime());

        user.setPermissions(this.getPermissions());
        user.setRoles(this.getRoles());
        user.setDept(this.getDept());
        return user;
    }
}
