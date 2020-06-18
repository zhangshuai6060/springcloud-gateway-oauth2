package com.example.springcloudalibabaoauthauth.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RedisConfig {

    @Bean
    public RedissonClient redissonClient() {
        //配置当前redis要连接的信息 redis://127.0.0.1:6379
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.3.37:6379").setPassword("zhangxiaoyun400").setDatabase(1);
        //config.useSingleServer().setAddress("redis://"+address+":"+port).setPassword(password).setDatabase(database);
        return Redisson.create(config);
    }


}
