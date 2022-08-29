package com.xinm.order.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: xinm
 * @Date: 2022/08/25 16:15
 * @Email: abc5232033@163.com
 * 库存服务接口
 * name:指定调用rest接口所对应的服务名
 * path:指定调用rest接口所在的StockController指定的@RequestMapping
 */
@FeignClient(name = "stock", path = "stock")
public interface StockClient {
    /**
     * 库存扣减
     *
     * @return
     */
    @RequestMapping("/subStockSeata")
    String subStockSeata(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
