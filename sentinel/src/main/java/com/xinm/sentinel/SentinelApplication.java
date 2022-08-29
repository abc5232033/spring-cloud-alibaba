package com.xinm.sentinel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: xinm
 * @Date: 2022/08/29 9:30
 * @Email: abc5232033@163.com
 */
@Slf4j
@EnableFeignClients
@SpringBootApplication
public class SentinelApplication {
    public static void main(String[] args) {
        SpringApplication.run(SentinelApplication.class, args);
        log.info("SentinelApplication start...");
    }
}
