package com.xinm.order.service;

import com.xinm.order.dao.OrderDao;
import com.xinm.order.entity.Order;
import com.xinm.order.feign.client.StockClient;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: xinm
 * @Date: 2022/08/29 16:07
 * @Email: abc5232033@163.com
 */
@Slf4j
@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private StockClient stockClient;


    /**
     * 保存订单
     * <p>
     *
     * @param order
     * @GlobalTransactional //分布式事务注解，这个一般放在业务层，这里图方便
     */
    @GlobalTransactional
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Order save(Order order) {
        // 保存订单
        orderDao.save(order);
        // 扣减库存
        String result = stockClient.subStockSeata(order.getProductId(), order.getCount());
        if (!"SUCCESS".equals(result)) {
            throw new RuntimeException("扣减库存失败");
        }
        int i = RandomUtils.nextInt(3);
        log.info("random number:{}", i);
        int t = 1 / i;
        return order;
    }

    /**
     * 查询订单列表
     *
     * @return
     */
    public List<Order> list() {
        return orderDao.findAll();
    }
}
