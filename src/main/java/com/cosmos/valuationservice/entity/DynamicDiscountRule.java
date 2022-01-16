package com.cosmos.valuationservice.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * tbl_dynamic_discount_rule
 * @author 
 */
@Data
public class DynamicDiscountRule implements Serializable {
    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 服务类型
     */
    private String serviceType;

    /**
     * 车辆级别
     */
    private String carLevel;

    /**
     * 时段设置
     */
    private String timeSet;

    /**
     * 日期类型 1星期 2特殊日期
     */
    private Integer dateType;

    /**
     * 星期设置
     */
    private String weekSet;

    /**
     * 特殊日期设置
     */
    private String specialDateSet;

    /**
     * 折扣封顶金额
     */
    private Double discountMaxPrice;

    /**
     * 生效开始时间
     */
    private Date validStartTime;

    /**
     * 生效结束时间
     */
    private Date validEndTime;

    /**
     * 是否不可用 0可用 1不可用
     */
    private Integer isUnuse;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}