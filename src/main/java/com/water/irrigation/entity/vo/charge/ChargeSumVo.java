package com.water.irrigation.entity.vo.charge;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="v_charge")
@Data
public class ChargeSumVo {
    @Id
    private String name;
    private Double value;
}
