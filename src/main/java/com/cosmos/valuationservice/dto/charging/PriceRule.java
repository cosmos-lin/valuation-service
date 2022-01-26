package com.cosmos.valuationservice.dto.charging;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 计费方法
 */
@Data
public class PriceRule {

    /**
     * 超公里单价 (元/公里)
     */
    private BigDecimal perKiloPrice;

    /**
     * 超时间单价 (元/分钟)
     */
    private BigDecimal perMinutePrice;

    /**
     * 分段计时规则
     */
    private BigDecimal timeRules;
}
