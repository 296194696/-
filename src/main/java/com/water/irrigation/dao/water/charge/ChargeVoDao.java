package com.water.irrigation.dao.water.charge;

import com.water.irrigation.entity.vo.charge.ChargeSumVo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargeVoDao extends JpaRepository<ChargeSumVo,String> {
}
