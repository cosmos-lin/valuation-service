package com.cosmos.valuationservice.constant;

/**
 * 通用美剧接口
 */
public interface CodeEnum {

    /**
     * 返回枚举的code值
     * @return
     */
    int getCode();

    /**
     * 返回枚举的code值
     * @return 字符串格式的code值
     */
    default String getCodeAsString() {
        return "" + getCode();
    }
}
