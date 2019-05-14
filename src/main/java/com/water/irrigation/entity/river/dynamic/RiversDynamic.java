package com.water.irrigation.entity.river.dynamic;

import com.water.irrigation.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rivers_dynamic")
@Data
public class RiversDynamic extends BaseEntity {
    /**
     * 河流名称
     */
    private String sname;
    /**
     * 河流长度
     */
    private Double lenth;
    /**
     * 河长姓名
     */
    private String username;
    /**
     * 河长行政职务
     */
    private String spost;
    /**
     * 河段水位
     */
    private Double stage;
    /**
     * 河段流量
     */
    private Double flow;
    /**
     * 河段水质
     */
    private Integer quality;
    /**
     * 污染程度
     */
    private Integer pollution;
    /**
     * 污染源个数
     */
    private Integer sourcesnumber;
    /**
     * 污染源名称
     */
    private String sourcesname;
    /**
     * 污染位置
     */
    private String sourcesposition;
    /**
     * 河段安全情况
     */
    private String safety;

}
