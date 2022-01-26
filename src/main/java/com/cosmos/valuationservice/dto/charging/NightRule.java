package com.cosmos.valuationservice.dto.charging;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 夜间服务费
 */
@Data
public class NightRule {

    /**
     * 夜间时间段 - 开始
     */
    private Date start;

    /**
     * 夜间时间段 - 结束
     */
    private Date end;

    /**
     * 超公里加收单价 (元/公里)
     */
    private BigDecimal PerKiloPrice;

    /**
     * 超时间加收单价 (元/分钟)
     */
    private BigDecimal perMinutePrice;
}
