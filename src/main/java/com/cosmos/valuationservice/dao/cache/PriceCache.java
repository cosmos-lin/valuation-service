package com.cosmos.valuationservice.dao.cache;

import com.cosmos.valuationservice.constant.OrderRuleNames;
import com.cosmos.valuationservice.dto.PriceMeter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor
public class PriceCache {

    @NonNull
    private StringRedisTemplate redisTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 从缓存中获取计价对象
     * @param orderId 订单ID
     * @return 计价对象
     */
    public PriceMeter get(Integer orderId) {

        PriceMeter priceMeter = null;
        String key = generateKey(orderId);
        log.info("查找缓存orderId={}, PriceMeterInRedis={}", orderId, key);

        try {
            priceMeter = mapper.readValue(redisTemplate.opsForValue().get(key), PriceMeter.class);
            log.info("获取缓存orderId={}, PriceMeterInRedis={}", orderId, key);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.warn("orderId={}, Redis解析PriceMeter失败{}", orderId, key);
        }
        return priceMeter;
    }

    /**
     * 缓存计价对象
     * @param orderId 订单ID
     * @param priceMeter 计价对象
     * @param timeout 过期时间
     * @param unit 过期时间单位
     */
    public void set(Integer orderId, PriceMeter priceMeter, Long timeout, TimeUnit unit) {
        String key = generateKey(orderId);
        log.info("设置缓存orderId={}, PriceMeterInRedis={}", orderId, key);

        try {
            redisTemplate.opsForValue().set(key, mapper.writeValueAsString(priceMeter), timeout, unit);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除缓存
     * @param orderId 订单Id
     */
    public void delete(Integer orderId) {
        String key = generateKey(orderId);
        log.info("删除缓存orderId={}, RuleInRedis={]", orderId, key);
        redisTemplate.delete(key);
    }


    /**
     * 生成redis key
     * @param orderId 订单id
     * @return redis key
     */
    private String generateKey(Integer orderId) {
        return String.format("%s:%s:%s", OrderRuleNames.PREFIX, OrderRuleNames.PRICE, orderId);
    }
}
