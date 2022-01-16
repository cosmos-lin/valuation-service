package com.cosmos.valuationservice.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * tbl_order_rule_price_tag
 * @author 
 */
@Data
public class OrderRulePriceTag implements Serializable {
    private Integer id;

    /**
     * 订单_id
     */
    private Integer orderId;

    /**
     * 价格类型：0预约，1实际
     */
    private String category;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 标签价格
     */
    private Double tagPrice;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}