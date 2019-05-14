package com.water.irrigation.entity.water.rate;

import com.water.irrigation.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "water_rate")
@Data
public class Rate extends BaseEntity {

    /**
     * 用户水费
     */
    private Double suserrate;
    /**
     * 单位水费
     */
    private Double sdeptrate;
    /**
     * 年份
     */
    private Integer iyear;
    /**
     * 备注
     */
    private String snote;
}
