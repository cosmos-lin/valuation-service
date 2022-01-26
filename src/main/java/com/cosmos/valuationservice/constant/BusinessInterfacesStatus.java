package com.cosmos.valuationservice.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessInterfacesStatus implements  CodeEnum{

    /**
     * 操作成功
     */
    SUCCESS(0, "success"),

    /**
     * 操作失败
     */
    FAIL(1, "fail");

    private final int code;

    private final String value;
}
