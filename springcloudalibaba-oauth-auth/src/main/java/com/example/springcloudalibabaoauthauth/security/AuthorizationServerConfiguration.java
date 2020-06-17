package com.example.springcloudalibabaoauthauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/12 17:26
 * 4
 */
@Configuration
//这里 告诉我们 这是一个 oauth2.0的 一个认证服务器
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("jwtTokenStore")
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Bean
    public ClientDetailsService jdbcClientDetails() {
        // 基于 JDBC 实现，需要事先在数据库配置客户端信息
        return new JdbcClientDetailsService(dataSource);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 从数据库读取客户端配置
        clients.withClientDetails(jdbcClientDetails());
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        //自定义token返回格式
        // 设置令牌
        endpoints.authenticationManager(authenticationManager)
                //注意这里 存储了 用户的信息 当我去拿着token验证用户的时候 这个时候注意 存储的是 userdetailservice的信息
                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore) //配置令牌存储策略
                .accessTokenConverter(jwtAccessTokenConverter);
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                // 允许客户表单认证,不加的话/oauth/token无法访问
                .allowFormAuthenticationForClients()
                // 对于CheckEndpoint控制器[框架自带的校验]的/oauth/token端点允许所有客户端发送器请求而不会被Spring-security拦截
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 要访问/oauth/check_token必须设置为permitAll()，但这样所有人都可以访问了，设为isAuthenticated()又导致访问不了，这个问题暂时没找到解决方案
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()")
                .passwordEncoder(new BCryptPasswordEncoder());
    }

}
