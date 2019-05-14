package com.water.irrigation.dao.net.passenger;

import com.water.irrigation.entity.net.passenger.NetPassenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NetPassengerDao extends JpaRepository<NetPassenger,Long>, JpaSpecificationExecutor<NetPassenger> {

    NetPassenger findByUsername(String username);
}
