package com.example.springcloudalibabaoauthauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/12 17:03
 * 4
 */
@SpringBootApplication
@MapperScan("com.example.springcloudalibabaoauthauth.mapper")
public class SpringcloudalibabaOauthAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudalibabaOauthAuthApplication.class, args);
    }

}
