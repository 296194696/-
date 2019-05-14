package com.water.irrigation.entity.dto.water.charge;

import com.water.irrigation.entity.vo.charge.ChargeVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ChargeVoDto extends ChargeVo {

    /**
     * 页数
     */
    private Integer page = 1;

    /**
     * 每页记录数
     */
    private Integer limit = 10;

}
