package com.cosmos.valuationservice.dao;

import com.cosmos.valuationservice.entity.OrderRuleMirror;

public interface OrderRuleMirrorDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderRuleMirror record);

    int insertSelective(OrderRuleMirror record);

    OrderRuleMirror selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderRuleMirror record);

    int updateByPrimaryKey(OrderRuleMirror record);
}