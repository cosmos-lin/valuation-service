package com.cosmos.valuationservice.mapper;

import com.cosmos.valuationservice.entity.OrderRulePriceDetail;

public interface OrderRulePriceDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderRulePriceDetail record);

    int insertSelective(OrderRulePriceDetail record);

    OrderRulePriceDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderRulePriceDetail record);

    int updateByPrimaryKey(OrderRulePriceDetail record);
}