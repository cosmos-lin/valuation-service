package com.cosmos.valuationservice.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 计价规则种类枚举
 */
@Getter
@AllArgsConstructor  // 实现参数构造方法
public enum  ChargingCategoryEnum implements CodeEnum{

    /**
     *  预估订单：0
     */
    Forecast(0, "预约"),

    Settlement(1, "结算"),

    RealTime(2, "实时");

    private int code;
    private String name;
}
