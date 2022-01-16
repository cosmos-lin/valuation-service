package com.cosmos.valuationservice.dao;

import com.cosmos.valuationservice.entity.OrderRulePriceTag;

public interface OrderRulePriceTagDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderRulePriceTag record);

    int insertSelective(OrderRulePriceTag record);

    OrderRulePriceTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderRulePriceTag record);

    int updateByPrimaryKey(OrderRulePriceTag record);
}