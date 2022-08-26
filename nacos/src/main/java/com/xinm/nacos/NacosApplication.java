package com.xinm.nacos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: xinm
 * @Date: 2022/08/25 14:41
 * @Email: abc5232033@163.com
 * // @EnableDiscoveryClient
 * // 从Spring Cloud Edgware开始，@EnableDiscoveryClient可省略。只需加上相关依赖，并进行相应配置，即可将微服务注册到服务发现组件上。
 */
@Slf4j
@SpringBootApplication
public class NacosApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosApplication.class, args);
        log.info("NacosApplication started successfully ......");
    }
}
