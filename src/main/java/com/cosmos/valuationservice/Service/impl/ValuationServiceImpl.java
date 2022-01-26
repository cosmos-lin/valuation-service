package com.cosmos.valuationservice.Service.impl;

import com.cosmos.valuationservice.Service.ValuationService;
import com.cosmos.valuationservice.constant.ChargingCategoryEnum;
import com.cosmos.valuationservice.dao.OrderDao;
import com.cosmos.valuationservice.dao.cache.PriceCache;
import com.cosmos.valuationservice.dto.PriceMeter;
import com.cosmos.valuationservice.mapper.OrderMapper;
import com.cosmos.valuationservice.dto.DriveMeter;
import com.cosmos.valuationservice.dto.charging.Rule;
import com.cosmos.valuationservice.task.ValuationRequestTask;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;

/**
 * 计价服务
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ValuationServiceImpl implements ValuationService {

    @Autowired
    private ValuationRequestTask requestTask;

    @NonNull
    private OrderDao orderDao;

    @NonNull
    private PriceCache priceCache;

    @Override
    public BigDecimal calcForecastPrice(Integer orderId) {

        // 设置驾驶参数
        DriveMeter driveMeter = generateDriveMeter(orderId, ChargingCategoryEnum.Forecast);
        Rule rule = driveMeter.getRule();
        PriceMeter priceMeter = priceCache.get(orderId);
        if (null == priceMeter || null == rule || !ObjectUtils.nullSafeEquals(rule.getId(), priceMeter.getRuleId())) {
            // 计算价格
            priceMeter = generatePriceMeter(driveMeter);
        }

        return null;
    }

    private PriceMeter generatePriceMeter(DriveMeter driveMeter) {

        // 分段计价任务

        return null;
    }

    /**
     * 定义驾驶参数
     * @param orderId 订单ID
     * @param chargingCategory 计算规则种类
     * @return  封装行驶计价相关的请求参数的对象
     */
    @SneakyThrows
    private DriveMeter generateDriveMeter(Integer orderId, ChargingCategoryEnum chargingCategory) {

        Rule rule = requestTask.requestRule(orderId);
        DriveMeter driveMeter = new DriveMeter(chargingCategory);

        switch (chargingCategory) {
            case Forecast:
                driveMeter.setOrder(orderDao.selectByOrderId(orderId)).setRule(rule).setRequestTask(requestTask);
                driveMeter.setRoute(requestTask.requestRoute(driveMeter));
                break;
            case Settlement:
                driveMeter.setOrder(orderDao.selectByOrderId(orderId)).setRule(rule).setRequestTask(requestTask);
                driveMeter.setDistance(requestTask.requestDistance(driveMeter));
                break;
            case RealTime:
                driveMeter.setRule(rule).setRequestTask(requestTask);
                break;
            default:
                break;
        }

        return driveMeter;
    }
}
