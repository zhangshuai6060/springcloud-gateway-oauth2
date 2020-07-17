package com.example.springcloudalibabaoauthauth.util;

import lombok.Data;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

/**
 * @author zhangshuai
 */
@Data
public class JwtUser implements UserDetails, CredentialsContainer {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 权限
     */
    private Set<GrantedAuthority> authorities;

    /**
     * 账号
     */
    private boolean accountNonExpired;

    /**
     * 账号是否被锁定
     */
    private boolean accountNonLocked;

    /**
     *
     */
    private boolean credentialsNonExpired;

    /**
     * 用户是否可用
     */
    private boolean enabled;

    public JwtUser() {
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }



    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }
}
