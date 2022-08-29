package com.xinm.sentinel.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 库存服务接口
 * name:指定调用rest接口所对应的服务名
 * path:指定调用rest接口所在的StockController指定的@RequestMapping
 *
 * @Author: xinm
 * @Date: 2022/08/29 11:19
 * @Email: abc5232033@163.com
 */
@FeignClient(name = "stock", path = "stock", fallback = StockOpenFeignServiceFallback.class)
public interface StockOpenFeignService {

    //声明需要调用的rest接口对应的方法

    /**
     * 库存扣减
     *
     * @return // @RequestLine("GET /subStock") //feign的原生注解
     * // String subStock(@Param("id") String id); //@PathVariable换成@Param
     */
    @RequestMapping("/subStock")
    String subStock();

    /**
     * 库存新增
     * // @RequestLine("GET /addStock") //feign的原生注解
     */
    @RequestMapping("/addStock")
    String addStock();
}
