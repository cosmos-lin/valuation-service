package com.cosmos.valuationservice.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * tbl_order_rule_mirror
 * @author 
 */
@Data
public class OrderRuleMirror implements Serializable {
    private Integer id;

    /**
     * 订单_id
     */
    private Integer orderId;

    /**
     * 计价规则id
     */
    private Integer ruleId;

    /**
     * 规则镜像的json
     */
    private String rule;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}