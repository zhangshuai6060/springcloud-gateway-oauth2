package com.example.springcloudalibabaoauthauth.controller;

import com.example.springcloudalibabaoauthauth.dto.LoginDTO;
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
     */
    @PostMapping("/password/login")
    public String Login(@RequestBody LoginDTO loginDTO){
        //判断帐号
        if(StringUtils.isNotBlank(loginDTO.getUserName())){
            //返回异常出去
        }
        if(StringUtils.isNotBlank(loginDTO.getPassword())){
            //返回异常出去
        }
        //判断密码
        return null;
    }
}
