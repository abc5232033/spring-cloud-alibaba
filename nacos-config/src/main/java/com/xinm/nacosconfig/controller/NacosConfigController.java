package com.xinm.nacosconfig.controller;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @Author: xinm
 * @Date: 2022/08/26 9:34
 * @Email: abc5232033@163.com
 */
@RefreshScope
@RestController
@RequestMapping("/nacos-config")
public class NacosConfigController {
    @Value("${nacos-config.user.name}")
    private String userName;
    @Value("${nacos-config.user.age}")
    private Integer userAge;
    @Value("${common-config.desc}")
    private String commonDesc;



    @RequestMapping("/test")
    public Object test() {
        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("userName", userName);
        hashMap.put("userAge", userAge);
        hashMap.put("commonDesc", commonDesc);
        hashMap.put("now", LocalDateTime.now());
        return hashMap;
    }
}
