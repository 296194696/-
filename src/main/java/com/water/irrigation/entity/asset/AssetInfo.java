package com.water.irrigation.entity.asset;

import com.water.irrigation.entity.base.DataEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name="asset_info")
@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
public class AssetInfo extends DataEntity {

    /**  资产编号 **/
    private String assetno;
    /**  资产型号 **/
    private Integer model;
    /**  设备序列号 **/
    private String equipnumber;
    /**  设备名称 **/
    private String sname;
    /**  权属关系 **/
    private String belongto;
    /**  资产类别 **/
    private String type;
    /**  资产计量 **/
    private String measure;
    /**  生产日期 **/
    private Date productdate;
    /**  原始金额 **/
    private Double money;
    /**  资产状态 **/
    private String state;
    /**  使用部门 **/
    private String dept;
    /**  使用负责人 **/
    private String principal;
    /**  启用日期 **/
    private Date startusetime;
    /**  折旧率 **/
    private Double depreciation;
    /**  已使用年限 **/
    private Integer useryear;
    /**  规定使用年限 **/
    private Integer lifeyear;
    /**  备注 **/
    private String remark;

}
