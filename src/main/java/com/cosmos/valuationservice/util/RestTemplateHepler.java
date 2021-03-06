package com.cosmos.valuationservice.util;

import com.cosmos.valuationservice.dto.ResponseResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate 帮助类
 */
@Configuration
public class RestTemplateHepler {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 将ResponseResult解析为指定的类
     * @param result ResponseResult
     * @param clazz  指定的类
     * @param <T>    指定的类类型
     * @return        指定的类的
     * @throws JsonProcessingException  异常
     */
    public static <T> T parse(@Nullable ResponseResult result, Class<T> clazz) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(mapper.writeValueAsString(result != null ? result.getData() : null), clazz);
    }
}
