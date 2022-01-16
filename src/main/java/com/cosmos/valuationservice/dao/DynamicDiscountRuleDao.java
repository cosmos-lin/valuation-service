package com.cosmos.valuationservice.dao;

import com.cosmos.valuationservice.entity.DynamicDiscountRule;

public interface DynamicDiscountRuleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(DynamicDiscountRule record);

    int insertSelective(DynamicDiscountRule record);

    DynamicDiscountRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DynamicDiscountRule record);

    int updateByPrimaryKey(DynamicDiscountRule record);
}