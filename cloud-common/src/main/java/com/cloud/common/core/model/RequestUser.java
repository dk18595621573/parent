package com.cloud.common.core.model;

import com.cloud.common.constant.Constants;
import lombok.Data;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * 本次请求用户信息.
 *
 * @author zenghao
 * @date 2022/6/6
 */
@Data
public class RequestUser extends BaseRequestInfo {

    public static final RequestUser SYSTEM = new RequestUser(-1L,
        new Dept(-1L, "系统"), "system",
        Collections.singleton(Constants.ROLE_SYSTEM),
        Collections.singleton(Constants.ALL_PERMISSION));

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

    public RequestUser() {
    }

    public RequestUser(Long userId, Dept dept, String username) {
        this.setUserId(userId);
        this.setDeptId(dept.getDeptId());
        this.setUsername(username);
        this.setDept(dept);
    }

    public RequestUser(Long userId, Dept dept, String username, Set<Role> roles) {
        this.setUserId(userId);
        this.setDeptId(dept.getDeptId());
        this.setUsername(username);
        this.setDept(dept);
        this.setRoles(roles);
    }

    public RequestUser(Long userId, Dept dept, String username, Set<Role> roles, Set<String> permissions) {
        this.setUserId(userId);
        this.setDeptId(dept.getDeptId());
        this.setUsername(username);
        this.setPermissions(permissions);
        this.setDept(dept);
        this.setRoles(roles);
    }

    public void fromRequestInfo(BaseRequestInfo requestInfo) {
        if (Objects.nonNull(requestInfo)) {
            this.setIpaddr(requestInfo.getIpaddr());
            this.setLoginLocation(requestInfo.getLoginLocation());
            this.setBrowser(requestInfo.getBrowser());
            this.setOs(requestInfo.getOs());
        }
    }

}
