package com.example.springcloudalibabaoatuhgatewat.service;


import com.example.springcloudalibabaoatuhgatewat.util.AuthToken;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
public class AuthService {

    /** 这里指的是客户端的信息和密钥 具体看数据库表 oauth_client_details **/
    private final String clientId="client";
    private final String clientSecret="admin";

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Resource(name = "facePlusRestTemplate")
    RestTemplate restTemplate;

    /**
     * 调用服务 获取刷新令牌
     *
     * @param token
     * @return
     */
    public AuthToken refresh_token(String token) {
        //从nacos中获取认证服务的实例地址（因为spring security在认证服务中） 服务的名称
        ServiceInstance serviceInstance = loadBalancerClient.choose("springcloud-oauth-auth");
        //此地址就是http://ip:portx
        URI uri = serviceInstance.getUri();
        //令牌校验的地址 http://localhost:8086/auth/oauth/check_token
        String authUrl = uri + "/auth/oauth/token";

        //定义header
        LinkedMultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        String httpBasic = getHttpBasic(clientId, clientSecret);
        header.add("Authorization", httpBasic);

        //定义body
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "refresh_token");
        body.add("refresh_token", token);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, header);
        //调用登录认证服务 刷新jwt令牌
        ResponseEntity<Map> exchange = restTemplate.exchange(authUrl, HttpMethod.POST, httpEntity, Map.class);
        //申请令牌信息
        AuthToken authToken = makeAuthToken(exchange);
        return authToken;
    }

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

    //获取httpbasic的串
    private String getHttpBasic(String clientId, String clientSecret) {
        String string = clientId + ":" + clientSecret;
        //将串进行base64编码
        byte[] encode = Base64Utils.encode(string.getBytes());
        return "Basic " + new String(encode);
    }

}
