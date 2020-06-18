package com.example.springcloudalibabaoauthauth.service.impl;

import com.example.springcloudalibabaoauthauth.config.RedisMethod;
import com.example.springcloudalibabaoauthauth.service.TokenService;
import com.example.springcloudalibabaoauthauth.util.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.Map;

/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/18 14:47
 * 4
 */
@Service
public class TokenServiceImpl implements TokenService {
    /** 这里指的是客户端的信息和密钥 具体看数据库表 oauth_client_details **/
    private final String clientId="client";
    private final String clientSecret="client";

    //在redis里面的过期时间 3600秒
    private final Long ExpireTime=3600l;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Resource(name = "facePlusRestTemplate")
    RestTemplate restTemplate;

    @Autowired
    RedisMethod redisMethod;

    @Override
    public AuthToken passwordVerifyToken(String username, String password) {
        //拿着username 和password 去请求 /auth/oauth/token 来获取到token
        AuthToken token = this.passwordApplyToken(username, password);
        //如果错误信息 不为空的话  走 redis
        if (token.getError() == null && token.getError_description() == null) {
            //把token存储到redis当中
            redisMethod.setStringTime(token.getAccess_token(), token.toString(), ExpireTime);
        }
        return token;
    }

    private AuthToken passwordApplyToken(String username, String password) {
        //从nacos中获取认证服务的实例地址（因为spring security在认证服务中） 服务的名称
        ServiceInstance serviceInstance = loadBalancerClient.choose("springcloud-oauth-auth");
        //此地址就是http://ip:portx
        URI uri = serviceInstance.getUri();
        //令牌申请的地址 http://localhost:8086/auth/oauth/token
        String authUrl = uri + "/auth/oauth/token";
        //定义header
        LinkedMultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        String httpBasic = getHttpBasic();
        header.add("Authorization", httpBasic);
        //定义body
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("username", username);
        body.add("password", password);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, header);
        //调用登录认证服务 生成jwt令牌
        ResponseEntity<Map> exchange = restTemplate.exchange(authUrl, HttpMethod.POST, httpEntity, Map.class);
        //申请令牌信息
        AuthToken authToken = makeAuthToken(exchange);
        return authToken;
    }

    /**
     * 设置token值
     *
     * @param exchange 远程调用结果
     * @return
     */
    private AuthToken makeAuthToken(ResponseEntity<Map> exchange) {
        Map bodyMap = exchange.getBody();
        AuthToken authToken = new AuthToken();
        if (bodyMap == null ||
                bodyMap.get("access_token") == null ||
                bodyMap.get("refresh_token") == null ||
                bodyMap.get("jti") == null ||
                bodyMap.get("expires_in") == null
        ) {
            authToken.setError((String) bodyMap.get("error"));
            authToken.setError_description((String) bodyMap.get("error_description"));
            return authToken;
        }
        authToken.setAccess_token((String) bodyMap.get("access_token"));//用户身份令牌
        authToken.setRefresh_token((String) bodyMap.get("refresh_token"));//刷新令牌
        authToken.setJwt_token((String) bodyMap.get("jti"));//jwt令牌
        return authToken;
    }

    /*获取httpBasic的串*/
    private String getHttpBasic() {
        String string = clientId + ":" + clientSecret;
        //将串进行base64编码
        byte[] encode = Base64Utils.encode(string.getBytes());
        return "Basic " + new String(encode);
    }
}
