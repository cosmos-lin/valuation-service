package com.cosmos.valuationservice.dao;

import com.cosmos.valuationservice.entity.OrderRulePriceDetail;

public interface OrderRulePriceDetailDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderRulePriceDetail record);

    int insertSelective(OrderRulePriceDetail record);

    OrderRulePriceDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderRulePriceDetail record);

    int updateByPrimaryKey(OrderRulePriceDetail record);
}