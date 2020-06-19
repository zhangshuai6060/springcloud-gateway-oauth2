package com.example.springcloudalibabaoatuhgatewat.util;


import com.alibaba.fastjson.JSON;
import com.example.springcloudalibabaoatuhgatewat.entity.UserJwtVo;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;

import java.util.Map;

public class JwtUtils {
    //解析jwt令牌
    public static Map parsingJwt(String token) {
        //解析jwt
        Jwt decode = JwtHelper.decode(token);
        //得到 jwt中的用户信息
        String claims = decode.getClaims();
        //将jwt转为Map
        Map map = JSON.parseObject(claims, Map.class);
        return map;

    }

    /**
     * 获取jwt令牌用户信息
     *
     * @param token
     * @return
     */
    public static UserJwtVo getUserJwtFromToken(String token) {

        //直接根据token拿到用户的信息啊
        Map<String, String> jwtClaims = null;
        //解析jwt
        Jwt decode = JwtHelper.decode(token);
        //得到 jwt中的用户信息
        String claims = decode.getClaims();
        //将jwt转为Map
        jwtClaims = JSON.parseObject(claims, Map.class);
        UserJwtVo userJwt = new UserJwtVo();
        userJwt.setId(Long.parseLong(String.valueOf(jwtClaims.get("id"))));
        userJwt.setName(String.valueOf(jwtClaims.get("username")));
        return userJwt;
    }

}
