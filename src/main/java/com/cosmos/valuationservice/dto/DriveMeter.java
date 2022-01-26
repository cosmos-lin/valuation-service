package com.cosmos.valuationservice.dto;

import com.cosmos.valuationservice.constant.ChargingCategoryEnum;
import com.cosmos.valuationservice.dto.charging.Rule;
import com.cosmos.valuationservice.dto.map.Distance;
import com.cosmos.valuationservice.dto.map.Route;
import com.cosmos.valuationservice.dto.request.CurrentPriceRequestDto;
import com.cosmos.valuationservice.entity.Order;
import com.cosmos.valuationservice.task.ValuationRequestTask;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Optional;

/**
 * 行驶计价相关的请求参数：距离时间
 */

@Data
@RequiredArgsConstructor
@Accessors(chain = true)
public class DriveMeter {

    /**
     * 订单
     */
    private Order order;

    /**
     * 计价规则
     */
    private Rule rule;

    /**
     * 预估时距离测量结果：没行驶之前
     */
    private Route route;

    /**
     * 实际轨迹里程： 实际路程
     */
    private Distance distance;

    /**
     * 实时价格请求DTO
     */
    private CurrentPriceRequestDto currentPriceRequestDto;

    /**
     * 计价服务请求任务实体类
     */
    private ValuationRequestTask requestTask;

    @NonNull
    private ChargingCategoryEnum chargingCategoryEnum;

    /**
     * 返回行驶距离 (米)
     * @return 行驶距离
     */
    public double getTotalDistance() {
        Double meters = 0D;
        switch (chargingCategoryEnum) {
            case Forecast:
                meters = route.getDistance();
                break;
            case Settlement:
            case RealTime:
                meters = distance.getDistance();
                break;
            default:
                break;
        }
        return Optional.ofNullable(meters).orElse(0D);
    }
    
    public double getTotalTime() {
        
        return 0;
    }
}
