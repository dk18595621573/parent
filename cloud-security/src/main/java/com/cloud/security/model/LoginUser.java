package com.cloud.security.model;

import com.cloud.common.core.domain.model.RequestUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * 登录用户身份权限
 *
 * @author author
 */
@Data
public class LoginUser extends RequestUser implements UserDetails {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private String password;

    /**
     * 角色列表
     */
    private Set<Role> roles;

    /**
     * 部门信息.
     */
    private Dept dept;

    public LoginUser(Long userId, Dept dept, String username, String password) {
        this.setUserId(userId);
        this.setDeptId(dept.getDeptId());
        this.setUsername(username);
        this.dept = dept;
        this.password = password;
    }

    public LoginUser(Long userId, Dept dept, String username, String password, Set<Role> roles) {
        this.setUserId(userId);
        this.setDeptId(dept.getDeptId());
        this.setUsername(username);
        this.dept = dept;
        this.password = password;
        this.roles = roles;
    }

    public LoginUser(Long userId, Dept dept, String username, String password, Set<Role> roles, Set<String> permissions) {
        this.setUserId(userId);
        this.setDeptId(dept.getDeptId());
        this.setUsername(username);
        this.setPermissions(permissions);
        this.dept = dept;
        this.password = password;
        this.roles = roles;
    }

    /**
     * 账户是否未过期,过期无法验证
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     *
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * 角色信息。
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Role implements Serializable {
        /**
         * 角色ID
         */
        private Long roleId;

        /**
         * 角色名称
         */
        private String roleName;

        /**
         * 角色权限
         */
        private String roleKey;

        /**
         * 数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限；5：仅本人数据权限）
         */
        private String dataScope;
    }

    /**
     * 部门信息
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Dept implements Serializable {
        /**
         * 部门ID
         */
        private Long deptId;

        /**
         * 部门名称
         */
        private String deptName;

    }
}
