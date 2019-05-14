package com.water.irrigation.entity.river.staticriver;

import com.water.irrigation.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rivers_static")
@Data
public class RiversStatic extends BaseEntity {
    /**
     * 河流名称
     */
    private String sname;
    /**
     * 河流长度
     */
    private Double lenth;
    /**
     * 所属流域
     */
    private String sbasin;
    /**
     * 经过的政区
     */
    private String state;
    /**
     * 流量变幅
     */
    private Double discharge;
    /**
     * 河宽变幅
     */
    private Double widthriver;

}
