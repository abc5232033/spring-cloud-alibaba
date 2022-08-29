package com.xinm.openfeign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * 自定义feign拦截器
 *
 * @Author: xinm
 * @Date: 2022/08/25 17:05
 * @Email: abc5232033@163.com
 */
@Slf4j
public class CustomFeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        //写一些自己的业务逻辑 带上token 什么之类的
        log.info("执行openFeign自定义拦截器");
        String access_token = UUID.randomUUID().toString();
        requestTemplate.header("Authorization", access_token);//设置认证
    }
}
