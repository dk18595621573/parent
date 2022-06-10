package com.cloud.security.model;

import com.cloud.common.core.domain.entity.SysUser;
import com.cloud.common.core.domain.model.RequestUser;
import com.cloud.common.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * 登录用户身份权限
 *
 * @author author
 */
public class LoginUser extends RequestUser implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String username;
    /**
     * 用户信息
     */
    private SysUser user;

    public LoginUser() {
    }

//    public LoginUser(SysUser user, Set<String> permissions) {
//        this.user = user;
//        this.permissions = permissions;
//    }
//
    public LoginUser(Long userId, Long deptId, SysUser user, Set<String> permissions) {
        this.setUserId(userId);
        this.setDeptId(deptId);
        this.setPermissions(permissions);
        this.user = user;
    }
//
//    public LoginUser(Long userId, Long deptId, Set<String> permissions) {
//        this.userId = userId;
//        this.deptId = deptId;
//        this.user = new SysUser();
//        this.permissions = permissions;
//    }


    public void setUsername(final String username) {
        this.username = username;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return StringUtils.defaultString(this.username, user.getUserName());
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


    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
