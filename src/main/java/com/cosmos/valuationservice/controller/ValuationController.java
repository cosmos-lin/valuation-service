package com.cosmos.valuationservice.controller;

import com.cosmos.valuationservice.Service.ValuationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor //代替@Autowired注解，需要注意的是在注入时需要用final定义，或者使用@notnull注解
@RequestMapping("/valuation")
public class ValuationController {

    private static final String ERR_CALC_FORECAST_PRICE = "订单预估加工计算错误";
    private static final String ERR_DONE_FORECAST = "订单接收预估错误";
    private static final String ERR_CALC_CURRENT_PRICE = "计算订单价格错误";
    private static final String ERR_CALC_SETTLEMENT_PRICE = "订单结算价格计算错误";

    @NonNull
    private ValuationService valuationService;
}
