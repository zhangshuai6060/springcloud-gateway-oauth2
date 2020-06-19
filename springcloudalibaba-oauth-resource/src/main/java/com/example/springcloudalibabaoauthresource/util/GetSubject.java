package com.example.springcloudalibabaoauthresource.util;


import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/19 14:30
 * 4   获取当前用户的信息
 */
@Component
public class GetSubject {


    public Subject getSubject(OAuth2Authentication oAuth2Authentication) {
        OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) oAuth2Authentication.getDetails();
        LinkedHashMap details = (LinkedHashMap) oAuth2AuthenticationDetails.getDecodedDetails();
        Subject subject=new Subject();
        //获取自定义的id
        subject.setId(Long.parseLong(details.get("id").toString()));
        //
        subject.setUsername(String.valueOf(details.get("username")));
        return subject;
    }

}
