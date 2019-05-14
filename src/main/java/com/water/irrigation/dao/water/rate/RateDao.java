package com.water.irrigation.dao.water.rate;

import com.water.irrigation.entity.water.rate.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface RateDao extends JpaRepository<Rate,Long>, JpaSpecificationExecutor<Rate> {

    @Query(value = "SELECT * FROM `water_rate` ORDER BY  indocno desc LIMIT 1" ,nativeQuery = true)
    Rate findMaxIndocno();
}
