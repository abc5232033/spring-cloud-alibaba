package com.xinm.openfeign.controller;

import com.xinm.openfeign.feign.client.StockClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xinm
 * @Date: 2022/08/25 16:14
 * @Email: abc5232033@163.com
 */
@Slf4j
@RestController
@RequestMapping("/openfeign")
public class OpenFeignController {
    @Autowired
    private StockClient stockClient;

    /**
     * 新增订单
     *
     * @return
     */
    @RequestMapping("/addOrder")
    public String addOrder() {
        log.info("订单新增成功");
        //调用库存扣减
        String result = stockClient.subStock();
        return "订单服务-订单新增成功:" + result;
    }
}
