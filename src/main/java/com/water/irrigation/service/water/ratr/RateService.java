package com.water.irrigation.service.water.ratr;

import com.water.irrigation.entity.dto.water.rate.RateDto;
import com.water.irrigation.entity.water.rate.Rate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 水概率信息服务层接口
 * @author lichunlei
 *
 */
public interface RateService {
    /**
     * 根据条件获取对应的水概率信息数据
     *
     * @param conditions 水概率信息过滤条件
     * @param pageable   分页
     * @return 指定的水概率信息列表
     */
    Page<Rate> findAll(RateDto conditions, Pageable pageable);

    Rate findOne(Long indocno);

    Rate add(Rate rate);

    Rate update(Rate rate);

    void delete(List<Rate> sysCompanys);

    Rate findMaxIndocno();
}
