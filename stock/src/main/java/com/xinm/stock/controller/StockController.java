package com.xinm.stock.controller;

import com.xinm.stock.service.StockService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: xinm
 * @Date: 2022/08/25 15:24
 * @Email: abc5232033@163.com
 */
@Slf4j
@RestController
@RequestMapping("/stock")
public class StockController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private StockService stockService;

    /**
     * 库存新增
     *
     * @return
     */
    @SneakyThrows
    @RequestMapping("/addStock")
    public String addStock() {
        TimeUnit.SECONDS.sleep(RandomUtils.nextInt(3));
        log.info("库存新增成功");
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
        log.info("库存扣减成功");

        return "库存服务-库存扣减成功 - 端口：" + port;
    }

    /**
     * 库存扣减
     *
     * @return
     */
    @RequestMapping("/subStockSeata")
    public String subStock(Long productId, Integer count) {
        int i = RandomUtils.nextInt(3);
        log.info("random number:{}", i);
        int t = 1 / i;
        // 扣减库存
        boolean result = stockService.subStock(productId, count);
        log.info("库存服务-库存扣减{} - 端口：{}", result ? "成功" : "失败", port);
        return result ? "SUCCESS" : "FAILED";
    }

    /**
     * 查询所有库存
     *
     * @return
     */
    @RequestMapping("/list")
    public Object list() {
        return stockService.list();
    }
}
