package com.example.springcloudalibabaoauthauth.result;

import com.example.springcloudalibabaoauthauth.util.AuthCode;
import lombok.Data;

/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/18 15:20
 * 4
 */
@Data
public class LoginResult {

    AuthCode authCode;

    public LoginResult(AuthCode authCode){
        this.authCode=authCode;
    }

}
