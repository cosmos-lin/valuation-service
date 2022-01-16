package com.cosmos.valuationservice.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * tbl_order_rule_price_detail
 * @author 
 */
@Data
public class OrderRulePriceDetail implements Serializable {
    private Integer id;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 价格类型：0预约，1实际
     */
    private String category;

    /**
     * 时间段-开始
     */
    private Integer startHour;

    /**
     * 时间段-结束
     */
    private Integer endHour;

    /**
     * 超公里单价(每公里单价)
     */
    private Double perKiloPrice;

    /**
     * 超时间单价(每分钟单价)
     */
    private Double perMinutePrice;

    /**
     * 该时间段的时间统计
     */
    private Double duration;

    /**
     * 该时间段的时间价格合计
     */
    private Double timePrice;

    /**
     * 该时间段的距离统计
     */
    private Double distance;

    /**
     * 该时间段的距离价格合计
     */
    private Double distancePrice;

    /**
     * create_time
     */
    private Date createTime;

    /**
     * update_time
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}