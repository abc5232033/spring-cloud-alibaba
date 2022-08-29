package com.xinm.stock.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinm.stock.entity.Stock;
import org.springframework.stereotype.Repository;

/**
 * @Author: xinm
 * @Date: 2022/08/29 17:24
 * @Email: abc5232033@163.com
 */
@Repository
public class StockDaoWrapper extends ServiceImpl<StockDao, Stock> {

    /**
     * 更新库存
     *
     * @param productId 商品id
     * @param count     扣减数量
     * @return
     */
    public boolean subStock(Long productId, Integer count) {
        return lambdaUpdate().setSql("count = count - " + count).eq(Stock::getProductId, productId).update();
    }
}
