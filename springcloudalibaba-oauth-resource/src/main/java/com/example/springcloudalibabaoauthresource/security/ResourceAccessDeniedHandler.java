package com.example.springcloudalibabaoauthresource.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/12 17:03
 * 4  重新定义security的权限不足信息
 */

@Component
public class ResourceAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        //返回json形式的错误信息
        HashMap<String, String> map = new HashMap<>();
        map.put("code", "500");
        map.put("msg", "没有此权限");
        map.put("uri", httpServletRequest.getRequestURI());
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper objectMapper = new ObjectMapper();
        String resBody = objectMapper.writeValueAsString(map);
        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.print(resBody);
        printWriter.flush();
        printWriter.close();
    }
}
