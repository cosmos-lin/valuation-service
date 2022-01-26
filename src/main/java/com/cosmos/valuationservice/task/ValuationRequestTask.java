package com.cosmos.valuationservice.task;

import com.cosmos.valuationservice.dto.DriveMeter;
import com.cosmos.valuationservice.dto.ResponseResult;
import com.cosmos.valuationservice.dto.map.Distance;
import com.cosmos.valuationservice.dto.map.Route;
import com.cosmos.valuationservice.mapper.OrderRuleMirrorMapper;
import com.cosmos.valuationservice.dao.cache.RuleCache;
import com.cosmos.valuationservice.dto.charging.Rule;
import com.cosmos.valuationservice.entity.OrderRuleMirror;
import com.cosmos.valuationservice.util.RestTemplateHepler;
import com.cosmos.valuationservice.util.ServiceAddress;
import com.cosmos.valuationservice.util.UnitConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * 计价请求任务
 */
@Component
@Slf4j
@AllArgsConstructor
public class ValuationRequestTask {

    @NonNull
    private RuleCache ruleCache;

    @NonNull
    private OrderRuleMirrorMapper orderRuleMirrorMapper;

    @NonNull
    private ServiceAddress serviceAddress;

    @NonNull
    private RestTemplate restTemplate;

    /**
     * 将Json解析为Rule
     * @param orderId 订单id
     * @return rule 实例
     */
    @SneakyThrows
    public Rule requestRule(Integer orderId) {
        Rule rule;
        try {
            // 从缓存冲获取规则
            rule = ruleCache.get(orderId);
            if (null != rule) return rule;

            // 否则；查询数据库中的规则, 并缓存
            OrderRuleMirror orderRuleMirror = orderRuleMirrorMapper.selectByOrderId(orderId);
            String ruleJson = orderRuleMirror.getRule();
            log.info("orderId={}, RuleJson={}", orderId, ruleJson);

            ObjectMapper objectMapper = new ObjectMapper();
            rule = objectMapper.readValue(ruleJson, Rule.class);
            ruleCache.set(orderId, rule);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("order={}, 解析RuleJson错误：", orderId, e);
            throw e;
        }
        return rule;
    }

    /**
     * 获取长途和行驶时间
     * @param driveMeter 行驶信息
     * @return 行驶信息
     */
    @SneakyThrows
    public Route requestRoute(DriveMeter driveMeter) {
        Route route;

        try {
            HashMap<String, Object> map = new HashMap<>(4);
            map.put("originLongitude", driveMeter.getOrder().getStartLongitude());
            map.put("originLatitude", driveMeter.getOrder().getStartLatitude());
            map.put("destinationLongitude", driveMeter.getOrder().getEndLongitude());
            map.put("destinationLatitude", driveMeter.getOrder().getEndLatitude());
            log.info("orderId={}, Request Route={}", driveMeter.getOrder().getId(), map);

            // 组装请求参数
            String params = map.keySet().stream().map(k -> k + "={" + k + "}").collect(Collectors.joining("&"));
            String url = serviceAddress.getMapAddress() + "/distance?" + params;
            ResponseResult result = restTemplate.getForObject(url, ResponseResult.class, map);
            log.info("调用接口Route，rusult: {}", result);
            route = RestTemplateHepler.parse(result, Route.class);
            if (null == route.getDuration() || null == route.getDuration()) {
                throw new Exception("Route内容为空：" + route);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("orderId={}, 调用接口Route错误: driveMeter={}", driveMeter.getOrder().getId(), driveMeter, e);
            throw e;
        }

        return route;
    }

    @SneakyThrows
    public Distance requestDistance(DriveMeter driveMeter) {
        try {
            int carId = driveMeter.getOrder().getCarId();
            String cityCode = driveMeter.getRule().getKeyRule().getCityCode();
            LocalDateTime startTime = UnitConverter.dateToLocalDateTime(driveMeter.getOrder().getReceivePassengerTime());
            LocalDateTime endTime = UnitConverter.dateToLocalDateTime(driveMeter.getOrder().getPassengerGetoffTime());
            log.info("orderId={}", driveMeter.getOrder().getId());

            return requestDistance(carId, cityCode, startTime, endTime);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("orderId={}, 调用接口Distance错误：driveMeter={}", driveMeter.getOrder().getId(), driveMeter, e);
            throw e;
        }
    }

    @SneakyThrows
    private Distance requestDistance(int carId, String cityCode, LocalDateTime startTime, LocalDateTime endTime) {

        HashMap<String, Object> map = new HashMap<>(4);
        Distance result = new Distance();
        result.setDistance(0D);

        try {
            // 按天分割计算
            long totalSeconds = Duration.between(startTime, endTime).toMillis();
            long intervalSeconds = Duration.ofDays(1).minusSeconds(1).toMillis();

            // 计算次数
            int times = (int) Math.ceil(1.0 * totalSeconds / intervalSeconds);
            for (int i = 0; i < times; i++) {
                long startSecond = startTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() + (i * intervalSeconds);
                long endSecond = Math.min(endTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(), startSecond + intervalSeconds);

                map.clear();
                map.put("vehicleId", carId);
                map.put("city", cityCode);
                map.put("startTime", startSecond);
                map.put("endTime", endSecond);
                log.info("Request Distance={}", map);

                String param = map.keySet().stream().map(k -> k + "={" + k + "}").collect(Collectors.joining("&"));
                String url = serviceAddress.getMapAddress() + "/route/distance?" + param;
                ResponseResult responseResult = restTemplate.getForObject(url, ResponseResult.class, map);
                Distance distance = RestTemplateHepler.parse(responseResult, Distance.class);

                if (null == distance || null == distance.getDistance()) {
                    throw new Exception("distance内容为空: " + result);
                }

                result.setDistance(result.getDistance() + distance.getDistance());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("调用接口Route Dsitance 错误；carId={}, cityCode={}, startTime={}, endTime={}", carId, cityCode,
                    startTime, endTime);
            throw e;
        }
        return result;
    }
}
