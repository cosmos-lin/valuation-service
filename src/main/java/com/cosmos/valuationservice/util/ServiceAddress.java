package com.cosmos.valuationservice.util;

import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 调用服务接口地址
 */
@Component
public class ServiceAddress {

    private List<Map<String, String>> address = new ArrayList<>();

    public static final String MAP_SERVER_URL = "map";

    /**
     * 获取服务接口地址
     * @param key 接口名
     * @return 地址
     */
    public String get(String key) {
        return address.stream().filter(m -> m.containsKey(key)).findFirst().orElse(new HashMap<>(0)).get(key);
    }

    /**
     * 获取地图服务接口地址
     * @return 地图服务接口地址
     */
    public String getMapAddress() {
        return get(MAP_SERVER_URL);
    }
}
