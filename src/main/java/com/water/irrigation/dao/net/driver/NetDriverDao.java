package com.water.irrigation.dao.net.driver;

import com.water.irrigation.entity.net.driver.NetDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NetDriverDao extends JpaRepository<NetDriver,Long>,JpaSpecificationExecutor<NetDriver> {

    NetDriver findByIndocno(Long indocno);

    NetDriver findByUsername(String username);

}
