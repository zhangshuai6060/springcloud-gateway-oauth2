package com.example.springcloudalibabaoatuhgatewat;

import com.example.springcloudalibabaoatuhgatewat.config.FacePlusThrowErrorHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class SpringcloudalibabaOatuhGatewatApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudalibabaOatuhGatewatApplication.class, args);
    }

    @Bean
    public RestTemplate facePlusRestTemplate() {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(300000);
        requestFactory.setReadTimeout(300000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        restTemplate.setErrorHandler(new FacePlusThrowErrorHandler());
        return restTemplate;
    }

}
