package com.xinm.stock.service;

import com.xinm.stock.dao.StockDaoWrapper;
import com.xinm.stock.entity.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xinm
 * @Date: 2022/08/29 17:25
 * @Email: abc5232033@163.com
 */
@Slf4j
@Service
public class StockService {
    @Autowired
    private StockDaoWrapper stockDaoWrapper;

    /**
     * 创建库存
     *
     * @param stock
     * @return
     */
    public boolean save(Stock stock) {
        return stockDaoWrapper.save(stock);
    }

    /**
     * 更新库存
     *
     * @param productId 商品id
     * @param count     扣减数量
     * @return
     */
    public boolean subStock(Long productId, Integer count) {
        return stockDaoWrapper.subStock(productId, count);
    }

    /**
     * 查询所有库存
     *
     * @return
     */
    public List<Stock> list() {
        return stockDaoWrapper.list();
    }
}
