package com.example.springcloudalibabaoauthresource.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;


/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/18 11:52
 * 4 这个注解表示 当前是一个资源服务器
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Autowired
    JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    TokenStore tokenStore;

    /**
     * 自定义 资源服务器 权限认证失败的异常
     */
    @Bean
    public AccessDeniedHandler getAccessDeniedHandler() {
        return new ResourceAccessDeniedHandler();
    }

    /**
     * 自定义 资源服务器 未经授权异常
     *
     * @return
     */
    @Bean
    public AuthenticationEntryPoint getAuthenticationEntryPoint() {
        return new ResourceAuthenticationEntryPoint();
    }

    /**
     * 设置资源服务器的 token 和自定义的异常
     *
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore)
                .accessDeniedHandler(getAccessDeniedHandler())
                .authenticationEntryPoint(getAuthenticationEntryPoint());
    }

}

