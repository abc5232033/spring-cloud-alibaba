package com.xinm.sentinel.openfeign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: xinm
 * @Date: 2022/08/29 11:18
 * @Email: abc5232033@163.com
 */
@Slf4j
@Component
public class StockOpenFeignServiceFallback implements StockOpenFeignService {

    @Override
    public String subStock() {
        log.warn("subStock-服务降级了！");
        return "subStock-服务降级了！";
    }

    @Override
    public String addStock() {
        log.warn("addStock-服务降级了！");
        return "addStock-服务降级了！";
    }
}
