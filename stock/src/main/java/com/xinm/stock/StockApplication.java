package com.xinm.stock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: xinm
 * @Date: 2022/08/25 15:21
 * @Email: abc5232033@163.com
 */
@Slf4j
@SpringBootApplication
public class StockApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockApplication.class, args);
        log.info("StockApplication started successfully ......");
    }
}
