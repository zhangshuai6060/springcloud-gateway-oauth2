package com.example.springcloudalibabaoauthauth.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springcloudalibabaoauthauth.entity.SysAuthUser;
import com.example.springcloudalibabaoauthauth.entity.SysMenu;
import com.example.springcloudalibabaoauthauth.entity.SysRoleMenu;
import com.example.springcloudalibabaoauthauth.entity.SysRoleUser;
import com.example.springcloudalibabaoauthauth.mapper.SysAuthUserMapper;
import com.example.springcloudalibabaoauthauth.mapper.SysMenuMapper;
import com.example.springcloudalibabaoauthauth.mapper.SysRoleMenuMapper;
import com.example.springcloudalibabaoauthauth.mapper.SysRoleUserMapper;
import com.example.springcloudalibabaoauthauth.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/12 17:03
 * 4
 */
@Service
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    SysAuthUserMapper authUserMapper;

    @Autowired
    SysRoleUserMapper sysUserRoleMapper;

    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysMenuMapper menuMapper;

    //根据用户名来查询这个用户
    public SysAuthUser findByUserName(String name) {
        LambdaQueryWrapper<SysAuthUser> lambda = new QueryWrapper<SysAuthUser>().lambda();
        lambda.eq(SysAuthUser::getUserName, name);
        return authUserMapper.selectOne(lambda);
    }


    public List<SysMenu> listMenuByUserId(long userId) {
        // 筛选出所有的角色id
        LambdaQueryWrapper<SysRoleUser> lambda = new QueryWrapper<SysRoleUser>().lambda();
        lambda.eq(SysRoleUser::getAuthUserId, userId);
        List<SysRoleUser> roleUsers = sysUserRoleMapper.selectList(lambda);
        // 筛选出所有的角色id
        List<Long> roleIds = roleUsers.stream().map(SysRoleUser::getRoleId).collect(Collectors.toList());
        //根据角色来查询出来 对应的菜单
        LambdaQueryWrapper<SysRoleMenu> roleWrapper = new QueryWrapper<SysRoleMenu>().lambda();
        roleWrapper.in(SysRoleMenu::getRoleId, roleIds);
        List<SysRoleMenu> menuList = sysRoleMenuMapper.selectList(roleWrapper);
        //筛选所有的权限id
        List<Long> menu = menuList.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
        LambdaQueryWrapper<SysMenu> menuWrapper = new QueryWrapper<SysMenu>().lambda();
        menuWrapper.in(SysMenu::getId, menu);
        menuWrapper.eq(SysMenu::getLogicalDeleted, 1);
        return menuMapper.selectList(menuWrapper);
    }


}
