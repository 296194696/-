package com.water.irrigation.entity.water.charge;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.water.irrigation.entity.base.BaseEntity;
import com.water.irrigation.entity.sys.user.SysUser;
import com.water.irrigation.entity.water.crops.Crop;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "water_charge")
@Data
public class Charge extends BaseEntity {
    /**
     * 作物名称
     */
    private String sname;
    /**
     *需水量
     */
    private Double needwater;
    /**
     *水费
     */
    private Double watermoney;
    /**
     *0(未缴费),1(已缴费)
     */
    private Integer itype;
    /**
     *年份
     */
    private Integer iyear;
    /**
     *用户ID
     */
    @Column(insertable = false,updatable = false)
    private Long  iuserid;
    /**
     *地块ID
     */
    @Column(insertable = false,updatable = false)
    private Long  icropid;
    @JsonIgnore//防止死循环，导致栈溢出
    @JoinColumn(name="iuserid")//外键列的列名
    @ManyToOne(fetch= FetchType.LAZY)
    private SysUser sysUserCharge;

    @OneToOne(optional = false, cascade = CascadeType.ALL)//级联保存、修改、删除、同步
    @JoinColumn(name="icropid")
    private Crop crop;
}
