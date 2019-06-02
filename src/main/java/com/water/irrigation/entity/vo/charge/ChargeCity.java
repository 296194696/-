package com.water.irrigation.entity.vo.charge;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="v_charge_city")
@Data
public class ChargeCity {

    private String scity;
    private Integer iyear;
    @Id
    private BigDecimal watermoney;
}
