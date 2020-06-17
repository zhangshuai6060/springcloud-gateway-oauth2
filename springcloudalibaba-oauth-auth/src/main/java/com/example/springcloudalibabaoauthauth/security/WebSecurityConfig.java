package com.example.springcloudalibabaoauthauth.security;

import com.example.springcloudalibabaoauthauth.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/12 17:03
 * 4
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //使用Security的认证管理器  认证操作security 给我们来做
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    //使用security的默认密码加密方式
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean  //主要是配置这个Bean，用于授权服务器配置中注入
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    //配置security不拦截的请求  下面可以根据需求自定义 用,号隔开
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 将 check_token 暴露出去，否则资源服务器访问时报 403 错误
        //配置暴露出去的端点  不用登陆就可以访问
        web.ignoring().antMatchers("/password/login", "/logout", "/userjwt", "/ucenter/getuserext", "/mobile/login",
                "/openid/login",
                "/oauth/check_token", "/queryToken");
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        //禁止跨域请求
        http.csrf().disable()
                .httpBasic().and()
                .formLogin()
                .and()
                .authorizeRequests().anyRequest().authenticated();
    }
}


