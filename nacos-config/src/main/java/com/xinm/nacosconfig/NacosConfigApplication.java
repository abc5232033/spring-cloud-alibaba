package com.xinm.nacosconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: xinm
 * @Date: 2022/08/26 9:26
 * @Email: abc5232033@163.com
 */
@Slf4j
@SpringBootApplication
public class NacosConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigApplication.class, args);
        log.info("NacosConfigApplication start success");
    }
}
