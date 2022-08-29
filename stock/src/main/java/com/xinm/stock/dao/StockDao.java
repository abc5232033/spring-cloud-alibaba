package com.xinm.stock.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinm.stock.entity.Stock;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: xinm
 * @Date: 2022/08/29 17:10
 * @Email: abc5232033@163.com
 */
@Mapper
public interface StockDao extends BaseMapper<Stock> {

}
