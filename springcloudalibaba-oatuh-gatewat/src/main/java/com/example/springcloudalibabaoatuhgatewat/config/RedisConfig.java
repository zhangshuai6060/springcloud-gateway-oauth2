package com.example.springcloudalibabaoatuhgatewat.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RedisConfig {

    @Bean
    public RedissonClient redissonClient() {
        //配置当前redis要连接的信息 redis://127.0.0.1:6379
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setPassword("123").setDatabase(1);
        return Redisson.create(config);
    }


}
