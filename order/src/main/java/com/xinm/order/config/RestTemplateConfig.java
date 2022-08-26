package com.xinm.order.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * SpringBoot-RestTemplate实现调用第三方API
 *
 * @Author: xinm
 * @Date: 2022/08/25 15:30
 * @Email: abc5232033@163.com
 */
@Configuration
@Component
public class RestTemplateConfig {
    @Bean
    @LoadBalanced //使RestTemplate请求支持负载均衡
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder.build();
        return restTemplate;
    }
}
