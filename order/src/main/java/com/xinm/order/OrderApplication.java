package com.xinm.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @Author: xinm
 * @Date: 2022/08/25 15:18
 * @Email: abc5232033@163.com
 */
@Slf4j
@EnableJpaAuditing
@EnableFeignClients
@SpringBootApplication
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
        log.info("OrderApplication started successfully ......");
    }
}
