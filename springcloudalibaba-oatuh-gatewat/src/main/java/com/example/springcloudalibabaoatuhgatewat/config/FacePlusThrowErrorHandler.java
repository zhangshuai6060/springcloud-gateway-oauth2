package com.example.springcloudalibabaoatuhgatewat.config;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/17 10:11
 * 4  设置restTemplate远程调用时候，对400和401不让报错，并且正确返回错误信息数据
 */
public class FacePlusThrowErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return false;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
    }
}

