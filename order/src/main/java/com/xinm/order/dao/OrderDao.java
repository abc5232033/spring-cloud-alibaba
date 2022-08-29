package com.xinm.order.dao;

import com.xinm.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: xinm
 * @Date: 2022/08/29 16:02
 * @Email: abc5232033@163.com
 */
public interface OrderDao extends JpaRepository<Order, Long> {
}
