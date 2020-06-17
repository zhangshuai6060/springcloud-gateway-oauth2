package com.example.springcloudalibabaoauthauth.service;


import com.example.springcloudalibabaoauthauth.entity.SysAuthUser;
import com.example.springcloudalibabaoauthauth.entity.SysMenu;

import java.util.List;


/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/12 17:03
 * 4
 */
public interface AuthUserService {


    /**
     * 根据用户名去查询 当前用户是否存在
     *
     * @param name
     * @return
     */
    SysAuthUser findByUserName(String name);


    /**
     * 根据用户id来查询当前用户拥有的权限
     *
     * @param userId 用户id
     * @return
     */
    List<SysMenu> listMenuByUserId(long userId);


}
