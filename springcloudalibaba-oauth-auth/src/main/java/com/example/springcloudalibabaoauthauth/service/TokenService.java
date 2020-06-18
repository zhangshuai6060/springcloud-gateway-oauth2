package com.example.springcloudalibabaoauthauth.service;

import com.example.springcloudalibabaoauthauth.util.AuthToken;

/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/18 14:44
 * 4
 */
public interface TokenService {


    /**
     * 根据用户名密码获取令牌
     *
     * @param username 用户名
     * @param password 用户密码
     * @return
     */
    AuthToken passwordVerifyToken(String username, String password);
}
