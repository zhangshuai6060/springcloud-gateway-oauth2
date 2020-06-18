package com.example.springcloudalibabaoauthauth.controller;

import com.example.springcloudalibabaoauthauth.dto.LoginDTO;
import com.example.springcloudalibabaoauthauth.result.LoginResult;
import com.example.springcloudalibabaoauthauth.util.AuthCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/18 11:52
 * 4
 */
@RestController
public class LoginController {

    /**
     * 登录的方法
     * 这里应该怎么返回呢
     */
    @PostMapping("/password/login")
    public LoginResult Login(@RequestBody LoginDTO loginDTO){
        //判断帐号
        if(StringUtils.isNotBlank(loginDTO.getUserName())){
            //返回异常出去
            return new LoginResult(AuthCode.AUTH_USERNAME_NONE);
        }
        if(StringUtils.isNotBlank(loginDTO.getPassword())){
            //返回异常出去
            return new LoginResult(AuthCode.AUTH_PASSWORD_NONE);
        }
        //判断密码
        return null;
    }
}
