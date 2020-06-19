package com.example.springcloudalibabaoauthresource.controller;

import com.example.springcloudalibabaoauthresource.util.GetSubject;
import com.example.springcloudalibabaoauthresource.util.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/15 8:47
 * 4
 */
@RestController
public class HelloController {

    @Autowired
    GetSubject getSubject;

    @GetMapping("/hello")
    public String hello(OAuth2Authentication oAuth2Authentication) {
        Subject subject = getSubject.getSubject(oAuth2Authentication);
        System.out.println("subject = " + subject);
        return "Hello";
    }


}
