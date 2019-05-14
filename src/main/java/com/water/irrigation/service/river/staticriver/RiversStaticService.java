package com.water.irrigation.service.river.staticriver;

import com.water.irrigation.entity.dto.river.staticriver.RiversStaticDto;
import com.water.irrigation.entity.river.staticriver.RiversStatic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 河流静态信息服务层接口
 * @author lichunlei
 *
 */
public interface RiversStaticService {

    /**
     * 根据条件获取对应的河流静态信息数据
     *
     * @param conditions 河流静态信息过滤条件
     * @param pageable   分页
     * @return 指定的河流静态信息列表
     */
    Page<RiversStatic> findAll(RiversStaticDto conditions, Pageable pageable);

    RiversStatic findOne(Long indocno);

    RiversStatic add(RiversStatic riversStatic);

    RiversStatic update(RiversStatic riversStatic);

    void delete(List<RiversStatic> sysCompanys);
}
