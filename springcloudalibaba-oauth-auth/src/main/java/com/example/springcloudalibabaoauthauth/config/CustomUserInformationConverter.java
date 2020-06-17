package com.example.springcloudalibabaoauthauth.config;


import com.example.springcloudalibabaoauthauth.util.JwtUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/12 17:03
 * 4  自定义 jwt令牌 返回的内容
 */
@Component
public class CustomUserInformationConverter extends DefaultUserAuthenticationConverter {

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        LinkedHashMap response = new LinkedHashMap();
        String username = authentication.getName();
        Object principal = authentication.getPrincipal();
        JwtUser jwtUser = null;
        if (principal instanceof JwtUser) {
            jwtUser = (JwtUser) principal;
        }
        response.put("id", jwtUser.getId());
        response.put("username", username);
        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
            response.put("authorities", AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }

        return response;
    }

}
