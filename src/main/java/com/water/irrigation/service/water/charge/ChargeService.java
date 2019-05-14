package com.water.irrigation.service.water.charge;

import com.water.irrigation.entity.dto.water.charge.ChargeDto;
import com.water.irrigation.entity.dto.water.charge.ChargeVoDto;
import com.water.irrigation.entity.vo.charge.ChargeVo;
import com.water.irrigation.entity.water.charge.Charge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 水费缴费服务层接口
 * @author lichunlei
 *
 */
public interface ChargeService {
    /**
     * 根据条件获取对应的水费缴费数据
     *
     * @param conditions 水费缴费过滤条件
     * @param pageable   分页
     * @return 指定的水费缴费列表
     */
    Page<Charge> findAll(ChargeDto conditions, Pageable pageable);

    Charge findOne(Long indocno);

    Charge add(Charge charge);

    Charge update(Charge charge);

    void delete(List<Charge> sysCompanys);

    Page<ChargeVo> findUserByDyCondition(ChargeVoDto chargeVoDto
            , Pageable pageable);

    List<Charge> updateSum(Charge charge);

    Map<String, List> findSumBySname();
}
