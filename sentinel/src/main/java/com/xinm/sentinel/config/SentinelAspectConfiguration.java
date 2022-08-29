package com.xinm.sentinel.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 若您的应用使用了 Spring AOP（无论是 Spring Boot 还是传统 Spring 应用），
 * 您需要通过配置的方式将 SentinelResourceAspect 注册为一个 Spring Bean：
 *
 * @Author: xinm
 * @Date: 2022/08/29 9:46
 * @Email: abc5232033@163.com
 */
@Configuration
public class SentinelAspectConfiguration {
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}
