package com.water.irrigation.dao.water.charge;

import com.water.irrigation.entity.vo.charge.ChargeCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChargeCityDao extends JpaRepository<ChargeCity,String> {

    List<ChargeCity> findByIyear(Integer iyear);

    @Query(value = "select * from v_charge_city where iyear=:iyear ORDER BY indocno",nativeQuery = true)
    List<ChargeCity> findQuery(Integer iyear);
}
