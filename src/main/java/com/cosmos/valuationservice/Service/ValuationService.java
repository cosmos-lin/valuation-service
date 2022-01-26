package com.cosmos.valuationservice.Service;

import java.math.BigDecimal;

/**
 * 计价服务
 */
public interface ValuationService {

    /**
     * 计算预估价格
     * @param orderId  订单id
     * @return 预估价格
     */
    BigDecimal calcForecastPrice(Integer orderId);

}
