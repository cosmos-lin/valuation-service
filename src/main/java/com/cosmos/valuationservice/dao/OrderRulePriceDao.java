package com.cosmos.valuationservice.dao;

import com.cosmos.valuationservice.entity.OrderRulePrice;

public interface OrderRulePriceDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderRulePrice record);

    int insertSelective(OrderRulePrice record);

    OrderRulePrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderRulePrice record);

    int updateByPrimaryKey(OrderRulePrice record);
}