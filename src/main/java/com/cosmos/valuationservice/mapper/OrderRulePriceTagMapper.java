package com.cosmos.valuationservice.mapper;

import com.cosmos.valuationservice.entity.OrderRulePriceTag;

public interface OrderRulePriceTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderRulePriceTag record);

    int insertSelective(OrderRulePriceTag record);

    OrderRulePriceTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderRulePriceTag record);

    int updateByPrimaryKey(OrderRulePriceTag record);
}