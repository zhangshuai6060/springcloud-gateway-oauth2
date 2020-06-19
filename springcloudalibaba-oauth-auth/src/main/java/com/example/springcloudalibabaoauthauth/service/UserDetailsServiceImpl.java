package com.example.springcloudalibabaoauthauth.service;


import com.example.springcloudalibabaoauthauth.entity.SysAuthUser;
import com.example.springcloudalibabaoauthauth.entity.SysMenu;
import com.example.springcloudalibabaoauthauth.util.JwtUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/12 17:03
 * 4 自定义用户认证与授权
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    AuthUserService authUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysAuthUser authUser = authUserService.findByUserName(username);
        // 查出用户权限
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        List<SysMenu> menuList = authUserService.listMenuByUserId(authUser.getId());
        menuList.forEach(SysMenu -> {
            if (StringUtils.isNotBlank(SysMenu.getResource())) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(SysMenu.getResource());
                grantedAuthorities.add(grantedAuthority);
            }
        });
        // 构建返回信息
        JwtUser jwtUser = new JwtUser(authUser.getUserName(), authUser.getPassword(), grantedAuthorities);
        jwtUser.setId(authUser.getId());
        jwtUser.setUsername(authUser.getUserName());
        jwtUser.setEnabled(true);
        jwtUser.setAccountNonLocked(true);
        jwtUser.setAccountNonExpired(true);
        jwtUser.setCredentialsNonExpired(true);
        return jwtUser;
    }
}
