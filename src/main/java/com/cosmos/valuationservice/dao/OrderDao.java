package com.cosmos.valuationservice.dao;

import com.cosmos.valuationservice.entity.Order;
import com.cosmos.valuationservice.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class OrderDao {

    @NonNull
    private OrderMapper orderMapper;

    /**
     *  根据订单id 查询订单
     * @param orderId 订单id
     * @return 订单
     */
    public Order selectByOrderId(Integer orderId) {

        return orderMapper.selectByPrimaryKey(orderId);
    }
}
