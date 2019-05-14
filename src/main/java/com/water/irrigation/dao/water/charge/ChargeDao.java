package com.water.irrigation.dao.water.charge;

import com.water.irrigation.entity.vo.charge.ChargeSumVo;
import com.water.irrigation.entity.water.charge.Charge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 水费缴费信息Dao
 * @author lichunlei
 *
 */
public interface ChargeDao extends JpaRepository<Charge,Long>, JpaSpecificationExecutor<Charge> {

    List<Charge> findByIyear(Integer iyear);

//    @Query(value = "select sname,sum(watermoney) as watermoney from water_charge GROUP BY sname",nativeQuery = true)
//    List<ChargeSumVo> findSumByName();

}
