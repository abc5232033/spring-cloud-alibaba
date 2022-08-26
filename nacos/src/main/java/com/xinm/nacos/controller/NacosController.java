package com.xinm.nacos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xinm
 * @Date: 2022/08/25 15:09
 * @Email: abc5232033@163.com
 */
@RestController
@RequestMapping("/nacos")
public class NacosController {
    @GetMapping("/test")
    public String test() {
        return "Hello Nacos";
    }
}
