package com.cosmos.valuationservice.mapper;

import com.cosmos.valuationservice.entity.OrderRuleMirror;

public interface OrderRuleMirrorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderRuleMirror record);

    int insertSelective(OrderRuleMirror record);

    OrderRuleMirror selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderRuleMirror record);

    int updateByPrimaryKey(OrderRuleMirror record);

    OrderRuleMirror selectByOrderId(Integer orderId);
}