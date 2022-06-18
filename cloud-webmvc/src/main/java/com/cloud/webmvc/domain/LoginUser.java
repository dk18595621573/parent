package com.cloud.webmvc.domain;

import com.cloud.common.core.domain.model.Dept;
import com.cloud.common.core.domain.model.RequestUser;
import com.cloud.common.core.domain.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * 登录用户身份权限
 *
 * @author author
 */
@Data
@NoArgsConstructor
public class LoginUser extends RequestUser implements UserDetails {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private String password;

    public LoginUser(Long userId, Dept dept, String username, String password) {
        this.setUserId(userId);
        this.setDeptId(dept.getDeptId());
        this.setUsername(username);
        this.setDept(dept);
        this.password = password;
    }

    public LoginUser(Long userId, Dept dept, String username, String password, Set<Role> roles) {
        this.setUserId(userId);
        this.setDeptId(dept.getDeptId());
        this.setUsername(username);
        this.setDept(dept);
        this.setRoles(roles);
        this.password = password;
    }

    public LoginUser(Long userId, Dept dept, String username, String password, Set<Role> roles, Set<String> permissions) {
        this.setUserId(userId);
        this.setDeptId(dept.getDeptId());
        this.setUsername(username);
        this.setPermissions(permissions);
        this.setDept(dept);
        this.setRoles(roles);
        this.password = password;
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

}
