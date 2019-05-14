package com.water.irrigation.service.water.block;

import com.water.irrigation.entity.dto.water.block.BlockInfoDto;
import com.water.irrigation.entity.water.block.BlockInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 地块信息服务层接口
 * @author lichunlei
 *
 */
public interface BlockInfoService {
    /**
     * 根据条件获取对应的地块信息数据
     *
     * @param conditions 地块信息过滤条件
     * @param pageable   分页
     * @return 指定的地块信息列表
     */
    Page<BlockInfo> findAll(BlockInfoDto conditions, Pageable pageable);

    BlockInfo findOne(Long indocno);

    BlockInfo add(BlockInfo blockInfo);

    BlockInfo update(BlockInfo blockInfo);

    void delete(List<BlockInfo> sysCompanys);

}
