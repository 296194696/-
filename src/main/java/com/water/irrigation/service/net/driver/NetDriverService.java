package com.water.irrigation.service.net.driver;

import com.water.irrigation.entity.dto.net.driver.NetDriverDto;
import com.water.irrigation.entity.net.driver.NetDriver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 司机信息服务层接口
 * @author lichunlei
 *
 */
public interface NetDriverService {
    /**
     * 根据条件获取对应的司机信息数据
     *
     * @param conditions 司机信息过滤条件
     * @param pageable   分页
     * @return 司机信息信息列表
     */
    Page<NetDriver> findAll(NetDriverDto conditions, Pageable pageable);

    NetDriver findOne(Long indocno);

    NetDriver add(NetDriver netDriver);

    NetDriver update(NetDriver netDriver);

    void delete(List<NetDriver> netDrivers);

    NetDriver check(NetDriver NetDriver);

}
