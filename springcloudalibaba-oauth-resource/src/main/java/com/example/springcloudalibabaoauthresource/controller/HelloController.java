package com.example.springcloudalibabaoauthresource.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/15 8:47
 * 4
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }


}
