package com.water.irrigation.entity.vo.charge;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Data
public class ChargeVo {
    @Id
    private Integer iyear;
    private Double sum;
    private Double need;
    private Integer itype;
}
