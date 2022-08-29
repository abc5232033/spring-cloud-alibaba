package com.xinm.stock.controller;

import lombok.SneakyThrows;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: xinm
 * @Date: 2022/08/25 15:24
 * @Email: abc5232033@163.com
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    @Value("${server.port}")
    private String port;

    /**
     * 库存新增
     *
     * @return
     */
    @SneakyThrows
    @RequestMapping("/addStock")
    public String addStock() {
        TimeUnit.SECONDS.sleep(RandomUtils.nextInt(3));
        System.out.println("库存新增成功");

        return "库存服务-库存新增成功";
    }

    /**
     * 库存扣减
     *
     * @return
     */
    @SneakyThrows
    @RequestMapping("/subStock")
    public String subStock() {
        TimeUnit.SECONDS.sleep(RandomUtils.nextInt(3));
        System.out.println("库存扣减成功");

        return "库存服务-库存扣减成功 - 端口：" + port;
    }
}
