package com.example.springcloudalibabaoauthresource.security;

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

@Configuration
@EnableResourceServer  //这个注解表示 当前是一个资源服务器
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


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
     * 自定义资源服务器的token  认证  授权
     *
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore())
                .accessDeniedHandler(getAccessDeniedHandler())
                .authenticationEntryPoint(getAuthenticationEntryPoint());
    }

    /**
     * 自定义 验证token 为什么格式的token  这里是 jwt 要和认证服务器保持一致
     *
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }


    /**
     * 这里是 jwt要进行签名的一个秘钥
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtTokenEnhancer() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("557554");
        return jwtAccessTokenConverter;
    }

}

