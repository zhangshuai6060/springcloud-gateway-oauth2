package com.example.springcloudalibabaoauthauth.controller;

import com.example.springcloudalibabaoauthauth.config.RedisMethod;
import com.example.springcloudalibabaoauthauth.dto.LoginDTO;
import com.example.springcloudalibabaoauthauth.result.CommonCode;
import com.example.springcloudalibabaoauthauth.result.LoginResult;
import com.example.springcloudalibabaoauthauth.result.Result;
import com.example.springcloudalibabaoauthauth.service.TokenService;
import com.example.springcloudalibabaoauthauth.util.AuthCode;
import com.example.springcloudalibabaoauthauth.util.AuthToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    TokenService tokenService;

    @Autowired
    RedisMethod redisMethod;

    /**
     * 用户密码登录
     * @param login
     * @return
     */
    @PostMapping("/password/login")
    public LoginResult login(@RequestBody LoginDTO login) {
        //判断用户名和密码不能为空
        if (StringUtils.isEmpty(login.getUserName()) || login.getUserName() == null) {
            return new LoginResult(AuthCode.AUTH_USERNAME_NONE, null, null);
        }
        if (StringUtils.isEmpty(login.getPassword()) || login.getPassword() == null) {
            return new LoginResult(AuthCode.AUTH_PASSWORD_NONE,null,null);
        }
        //拿着用户名和密码去请求服务来获取 token
        //登录成功显示这个
        AuthToken token = tokenService.passwordVerifyToken(login.getUserName(), login.getPassword());
        return this.errorInfo(token);
    }


    /**
     * 登出
     * @param token
     * @return
     */
    @PostMapping("/logout")
    public Result logout(String token) {
        redisMethod.delString(token);
        return Result.ok("退出成功");
    }

    /*判断当前登录的详细异常信息*/
    private LoginResult errorInfo(AuthToken authToken) {
        //返回前端异常 告诉用户具体异常信息
        //用户锁定异常信息
        if (authToken.getError() != null && authToken.getError_description() == null) {
            //用户名不存在异常
            return new LoginResult(AuthCode.AUTH_ACCOUNT_NOTEXISTS, null, null);
        }
        if (authToken.getError() != null && authToken.getError_description().equals("User is disabled")) {
            return new LoginResult(AuthCode.AUTH_USER_LOCK, null, null);
        }
        if (authToken.getError() != null && authToken.getError_description().equals("Bad credentials")) {
            //密码错误异常
            return new LoginResult(AuthCode.AUTH_WRONG_PASSWORD, null, null);
        }
        if (authToken.getError() == null && authToken.getError_description() == null) {
            return new LoginResult(CommonCode.SUCCESS, authToken.getAccess_token(), authToken.getRefresh_token());
        }
        //用户登录异常
        return new LoginResult(AuthCode.AUTH_ABNORMAL, null, null);
    }

}
