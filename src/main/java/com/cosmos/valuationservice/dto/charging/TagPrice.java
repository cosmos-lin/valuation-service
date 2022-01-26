package com.cosmos.valuationservice.dto.charging;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 标签费用
 */
@Data
public class TagPrice {

    /**
     * 标签名称
     */
    private String name;

    /**
     * 标签费用
     */
    private BigDecimal price;
}
