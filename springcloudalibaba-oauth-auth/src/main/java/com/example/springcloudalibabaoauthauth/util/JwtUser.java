package com.example.springcloudalibabaoauthauth.util;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/12 17:03
 * 4
 */
@Data
public class JwtUser extends User {

    private Long id;

    private String username;

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

    //boolean 的值 默认为true


    public JwtUser(String username, String password,
                   Collection<? extends GrantedAuthority> authorities) {
        super(username, password, true, true, true, true, authorities);
    }


}

