package com.cosmos.valuationservice.dao.cache;

import com.cosmos.valuationservice.constant.OrderRuleNames;
import com.cosmos.valuationservice.dto.charging.Rule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RuleCache {

    @NonNull
    private StringRedisTemplate redisTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 从缓存中获取计价规则
     * @param orderId 订单ID
     * @return 计价规则
     */
    public Rule get(Integer orderId) {
        Rule rule = null;
        String key = generateKey(orderId);
        log.info("查找缓存orderId={}, RuleJsonInRedis={}", orderId, key);

        try {
            rule = mapper.readValue(redisTemplate.opsForValue().get(key), Rule.class);
            log.info("获取缓存orderId={}, RuleJsonInRedis={}", orderId, rule);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.warn("orderId={}, Redis解析Rule失败{}", orderId, key);
        }
        return rule;
    }

    /**
     * 生成redis key
     * @param orderId 订单Id
     * @return redis key
     */
    public String generateKey(Integer orderId) {

        return String.format("%s:%s:%s", OrderRuleNames.PREFIX, OrderRuleNames.RULE, orderId);
    }

    /**
     * 设缓存计价规则置
     * @param orderId
     * @param rule
     */
    public void set(Integer orderId, Rule rule) {
        String key = generateKey(orderId);
        log.info("设置缓存orderId={}, RuleInRedis={}", orderId, rule);
        try {
            redisTemplate.opsForValue().set(key, mapper.writeValueAsString(rule));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("orderId={}, rule={}, 存储redis异常, e:", orderId, rule, e);
        }
    }

    /**
     * 删除缓存
     * @param orderId 订单ID
     */
    public void delete(Integer orderId) {
        String key = generateKey(orderId);
        log.info("删除缓存， orderId={}, RuleInRedis={}", orderId, key);
        redisTemplate.delete(key);
    }
}
