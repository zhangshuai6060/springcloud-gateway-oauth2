package com.example.springcloudalibabaoauthauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;


/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/12 17:03
 * 4  使用Jwt存储token的配置
 */
@Configuration
public class JwtTokenStoreConfig {


    public static final String EncryptedFileName = "557554";


    @Resource
    private CustomUserInformationConverter customUserInformation;

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter(customUserInformation));
    }

    //使用非对称加密算法
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(CustomUserInformationConverter customUserInformationApadter) {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(EncryptedFileName);
//        //找到 加密文件所在的地址 读取classpath里面的 xm-jwt.jks文件
//        ClassPathResource pathResource = new ClassPathResource(EncryptedFile);
//        //秘钥配置时的密码
//        KeyStoreKeyFactory keyFactory = new KeyStoreKeyFactory(pathResource, Secret.toCharArray());
//        //使用keystore得到秘钥
//        KeyPair pair = keyFactory.getKeyPair(EncryptedFileName);
//        //设置秘钥
//        converter.setKeyPair(pair);
        DefaultAccessTokenConverter accessTokenConverter = (DefaultAccessTokenConverter) converter.getAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(customUserInformationApadter);
        return converter;
    }

}