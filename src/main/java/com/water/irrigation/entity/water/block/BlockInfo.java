package com.water.irrigation.entity.water.block;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.water.irrigation.entity.base.BaseEntity;
import com.water.irrigation.entity.sys.user.SysUser;
import com.water.irrigation.entity.water.crops.Crop;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "block_info")
@Data
//@JsonIgnoreProperties(value = {"product"})
public class BlockInfo extends BaseEntity {

    /**
     * 地块名称
     */
    private String sname;
    /**
     * 地块位置
     */
    private String splace;
    /**
     * 所属行政区划
     */
    private String sdept;
    /**
     * 土壤类型
     */
    private Integer ibtype;
    /**
     * 正方形X轴
     */
    private Double squarex;
    /**
     * 正方形Y轴
     */
    private Double squarey;
    /**
     * 所属用户或者单位id
     */
    @Column(insertable = false,updatable = false)
    private Long iuserid;
    /**
     * 乡镇
     */
    private String stownship;
    /**
     *村
     */
    private String svillage;
    /**
     * 市/县
     */
    private String scity;
    /**
     * 年份
     */
    private Integer iyear;
    /**
     * 面积
     */
    private Double sarea;
    /**
     * 多对一
     */
//    @JSONField(serialize = false)
    @JsonIgnore//防止死循环，导致栈溢出
    @JoinColumn(name="iuserid")//外键列的列名
    @ManyToOne(fetch= FetchType.LAZY)
    private SysUser sysUser;
    /**
     * 作物表信息
     */
    @OneToMany(fetch=FetchType.EAGER,cascade={CascadeType.REMOVE},mappedBy="blockInfo")
    private List<Crop> crops=new ArrayList<Crop>();

}
