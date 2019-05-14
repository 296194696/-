package com.water.irrigation.service.net.passenger;

import com.water.irrigation.entity.dto.net.passenger.NetPassengerDto;
import com.water.irrigation.entity.net.passenger.NetPassenger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 乘客信息服务层接口
 * @author lichunlei
 *
 */
public interface NetPassengerService {

    /**
     * 根据条件获取对应的乘客信息数据
     *
     * @param conditions 乘客信息过滤条件
     * @param pageable   分页
     * @return 乘客信息信息列表
     */
    Page<NetPassenger> findAll(NetPassengerDto conditions, Pageable pageable);

    NetPassenger findOne(Long indocno);

    NetPassenger add(NetPassenger netPassenger);

    NetPassenger update(NetPassenger netPassenger);

    void delete(List<NetPassenger> netPassengers);
}
