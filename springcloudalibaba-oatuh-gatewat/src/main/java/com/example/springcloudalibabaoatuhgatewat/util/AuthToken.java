package com.example.springcloudalibabaoatuhgatewat.util;

import lombok.Data;

/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/12 17:03
 * 4
 */
@Data
public class AuthToken {
    /**
     * access_token
     */
    private String access_token;

    /**
     * 刷新令牌token
     */
    private String refresh_token;
    /**
     * jwt令牌
     */
    private String jwt_token;//jwt令牌

    /**
     * 令牌到期时间
     */
    private Integer expires_in;

    /**
     * 错误
     */
    private String error;

    /**
     * 申请令牌的错误信息
     */
    private String error_description;

}
