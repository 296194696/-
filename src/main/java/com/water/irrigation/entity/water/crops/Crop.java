package com.water.irrigation.entity.water.crops;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.water.irrigation.entity.base.BaseEntity;
import com.water.irrigation.entity.water.block.BlockInfo;
import com.water.irrigation.entity.water.charge.Charge;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "water_crops")
@Data
public class Crop extends BaseEntity {

    /**
     * 作物名称
     */
    private String sname;
    /**
     * 每立方米需水量
     */
    private Double iquantity;
    /**
     * 年份
     */
    private Integer iyear;
    /**
     * 年份
     */
    private Double needwater;

    /**
     * 地块外键
     */
    private Long iblockid;

    @JsonIgnore//防止死循环，导致栈溢出
    @JoinColumn(name="iblockid",updatable = false,insertable = false)//外键列的列名
    @ManyToOne(fetch= FetchType.LAZY)
    private BlockInfo blockInfo;
    
    // 出现mappedBy的就是关系被维护端,由idCard维护, optional = false可以不设置
    @OneToOne(mappedBy = "crop", cascade = { CascadeType.ALL })//CascadeType.PERSIST 级联保存,CascadeType.MERGE 级联更新,CascadeType.REFRESH 级联刷新
    @JsonIgnore//防止死循环，导致栈溢出
    private Charge charge;
}
