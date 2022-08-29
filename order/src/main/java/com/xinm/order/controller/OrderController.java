package com.xinm.order.controller;

import com.xinm.order.entity.Order;
import com.xinm.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: xinm
 * @Date: 2022/08/25 15:29
 * @Email: abc5232033@163.com
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderService orderService;

    /**
     * 新增订单
     *
     * @return
     */
    @RequestMapping("/addOrder")
    public String addOrder() {
        log.info("订单新增成功");
        //调用库存扣减
        String apiReqResult = restTemplate.getForObject("http://stock/stock/subStock", String.class);
        return "订单服务-订单新增成功:" + apiReqResult;
    }

    /**
     * 保存订单
     *
     * @return
     */
    @RequestMapping("/save")
    public Object save(@RequestBody Order order) {
        // 保存订单
        return orderService.save(order);
    }

    /**
     * 查询订单列表
     *
     * @return
     */
    @RequestMapping("/list")
    public Object list() {
        return orderService.list();
    }
}
