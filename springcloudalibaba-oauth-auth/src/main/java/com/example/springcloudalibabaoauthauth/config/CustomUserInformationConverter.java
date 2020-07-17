package com.example.springcloudalibabaoauthauth.config;


import com.example.springcloudalibabaoauthauth.util.JwtUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/12 17:03
 * 4  自定义 jwt令牌 返回的内容
 */
@Component
public class CustomUserInformationConverter extends DefaultUserAuthenticationConverter {

    private Collection<? extends GrantedAuthority> defaultAuthorities;

    /**
     * 自定义 jwt令牌 返回的内容
     * @param authentication
     * @return
     */
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

    /**
     * 重写oauth2的extractAuthentication 方法 让刷新成功后token 也带着我们自定义的信息
     * @param map
     * @return
     */
    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        Object principal = map.get(USERNAME);
        Collection<? extends GrantedAuthority> authorities = getAuthorities(map);
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUsername(String.valueOf(map.get("username")));
        principal = jwtUser;
        return new UsernamePasswordAuthenticationToken(principal, "N/A", authorities);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
        if (defaultAuthorities == null) {
            defaultAuthorities = new ArrayList<>();
        }
        if (!map.containsKey(AUTHORITIES)) {
            return defaultAuthorities;
        }
        Object authorities = map.get(AUTHORITIES);
        if (authorities instanceof String) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);
        }
        if (authorities instanceof Collection) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils
                    .collectionToCommaDelimitedString((Collection<?>) authorities));
        }
        throw new IllegalArgumentException("Authorities must be either a String or a Collection");
    }

}
