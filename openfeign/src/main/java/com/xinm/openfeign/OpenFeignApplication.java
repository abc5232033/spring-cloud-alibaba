package com.xinm.openfeign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: xinm
 * @Date: 2022/08/25 16:13
 * @Email: abc5232033@163.com
 */
@Slf4j
@EnableFeignClients
@SpringBootApplication
public class OpenFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(OpenFeignApplication.class, args);
        log.info("OpenFeignApplication started successfully ......");
    }
}
