package com.water.irrigation.service.river.dynamic;

import com.water.irrigation.entity.dto.river.dynamic.RiversDynamicDto;
import com.water.irrigation.entity.river.dynamic.RiversDynamic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 河流动态信息服务层接口
 * @author lichunlei
 *
 */
public interface RiversDynamicService {

    /**
     * 根据条件获取对应的河流动态信息数据
     *
     * @param conditions 河流动态信息过滤条件
     * @param pageable   分页
     * @return 指定的河流动态信息列表
     */
    Page<RiversDynamic> findAll(RiversDynamicDto conditions, Pageable pageable);

    RiversDynamic findOne(Long indocno);

    RiversDynamic add(RiversDynamic riversDynamic);

    RiversDynamic update(RiversDynamic riversDynamic);

    void delete(List<RiversDynamic> sysCompanys);
}
