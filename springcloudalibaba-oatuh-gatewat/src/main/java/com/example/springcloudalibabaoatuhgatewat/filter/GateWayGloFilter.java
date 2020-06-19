package com.example.springcloudalibabaoatuhgatewat.filter;

import com.alibaba.fastjson.JSON;
import com.example.springcloudalibabaoatuhgatewat.config.RedisMethod;
import com.example.springcloudalibabaoatuhgatewat.entity.UserJwtVo;
import com.example.springcloudalibabaoatuhgatewat.service.AuthService;
import com.example.springcloudalibabaoatuhgatewat.util.AuthToken;
import com.example.springcloudalibabaoatuhgatewat.util.JwtUtils;
import com.example.springcloudalibabaoatuhgatewat.util.ResponseCodeEnum;
import com.example.springcloudalibabaoatuhgatewat.util.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/18 10:11
 * 4
 */
@Slf4j
@Component
public class GateWayGloFilter implements GlobalFilter , Ordered {

    public static final String Authorization = "Authorization";

    //在redis里面的过期时间 3600秒
    private final Long ExpireTime=3600l;

    /*不需要身份验证的路径  */
    public static final String NO_AUTH_PATH = "/auth/password/login,/auth/oauth/check_token,/auth/oauth/token";


    @Autowired
    private AuthService authService;

    @Autowired
    RedisMethod redisMethod;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //获取response对象
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        //获取request对象
        ServerHttpRequest request = exchange.getRequest();
        //没有登录 判断是否是白名单请求
        if (isAllowRequesr(request)) {
            //如果是 放行
            return chain.filter(exchange);
        }
        try {
            //获取header中的token
            List<String> tokenlist = request.getHeaders().get("token");
            if (CollectionUtils.isEmpty(tokenlist)) {
                //没有token,拦截请求
                return getVoidMono(serverHttpResponse, ResponseCodeEnum.TOKEN_MISSION);
            }
            String token = tokenlist.get(0);
            //如果当前token 不在 redis里面 那就证明当前 token是假的 token
            //如果是假的token 那么 请你去 认证服务器 认证去
            String Token = redisMethod.getString(token);
            if (StringUtils.isEmpty(Token)) {
                //把异常返回过去 告诉 他
                return getVoidMono(serverHttpResponse, ResponseCodeEnum.TOKEN_INVALID);
            }
            //如果当你 redis里面的时间 低于了多少秒之后 我去刷新你的token
            long time = redisMethod.getTime(token);
            //当用户登录超过三小时就重新刷新令牌
            if (time < 50) {
                //刷新token
                List<String> refreshTokenlist = request.getHeaders().get("refreshToken");
                String refreshToken = refreshTokenlist.get(0);
                //调用服务重新生成令牌
                AuthToken authToken = authService.refresh_token(refreshToken);
                if (!org.springframework.util.StringUtils.isEmpty(authToken)) {
                    String jsonString = JSON.toJSONString(authToken);
                    //删除Redis原有的令牌 并存入新的令牌
                    if (redisMethod.delString(token)) {
                        redisMethod.setStringTime(authToken.getAccess_token(), jsonString, ExpireTime);
                        //将令牌响应给前端
                        return returnsToken(serverHttpResponse, authToken);
                    }
                }
            }
            //去认证服务器登录
            //通过jwt对token进行解析获取用户信息
            UserJwtVo userJwtFromHeader = JwtUtils.getUserJwtFromToken(token);
            log.info("用户{}正在访问资源:{}", userJwtFromHeader.getName(), request.getPath());
            //权限判断 校验通过,请求头增强，放行
//            Map map = JwtUtils.parsingJwt(token);
//            String authorities = map.get("authorities").toString();
//            System.out.println(authorities);
//            if (!authorities.contains(path)) {
//                return getVoidMono(serverHttpResponse, ResponseCodeEnum.REFRESH_TOKEN_QUANXIANNOT);
//            }
            //增强请求头
            request.mutate().header(Authorization, "Bearer " + token, "token", token);
            // System.out.println(request.getHeaders().toString());
            //授权下面的服务 并且放行
            //放行
            return chain.filter(exchange);

        } catch (Exception e) {
            log.info("服务解析用户信息失败:", e);
            //内部异常 返回500
            exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            return exchange.getResponse().setComplete();
        }
    }

    /**
     * 创建response返回提示信息
     *
     * @param serverHttpResponse
     * @param responseCodeEnum
     * @return
     */
    private Mono<Void> getVoidMono(ServerHttpResponse serverHttpResponse, ResponseCodeEnum responseCodeEnum) {
        serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        ResponseResult responseResult = ResponseResult.error(responseCodeEnum.getCode(), responseCodeEnum.getMessage());
        DataBuffer dataBuffer = serverHttpResponse.bufferFactory().wrap(JSON.toJSONString(responseResult).getBytes());
        return serverHttpResponse.writeWith(Flux.just(dataBuffer));
    }

    /**
     * 创建response返回刷新令牌
     *
     * @param serverHttpResponse
     * @param o
     * @return
     */
    private Mono<Void> returnsToken(ServerHttpResponse serverHttpResponse, Object o) {
        serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        DataBuffer dataBuffer = serverHttpResponse.bufferFactory().wrap(JSON.toJSONString(o).getBytes());
        return serverHttpResponse.writeWith(Flux.just(dataBuffer));
    }

    /**
     * 判断请求是否在白名单
     *
     * @param request
     * @return
     */
    private boolean isAllowRequesr(ServerHttpRequest request) {
        //获取当前请求的path和method
        String path = request.getPath().toString();
        String method = request.getMethodValue();
        //判断是否允许
        if (StringUtils.startsWith(NO_AUTH_PATH, path)) {
            //是许可的路径 放行
            return true;
        }
        //不是白名单请求
        return false;
    }

    /*设置当前类在spring中的加载顺序*/
    @Override
    public int getOrder() {
        return -2;
    }
}
