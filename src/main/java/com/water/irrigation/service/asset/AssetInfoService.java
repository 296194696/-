package com.water.irrigation.service.asset;

import com.water.irrigation.entity.asset.AssetInfo;
import com.water.irrigation.entity.dto.asset.AssetInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 资产信息服务层接口
 * @author lichunlei
 *
 */
public interface AssetInfoService {

    /**
     * 根据条件获取对应的资产信息数据
     *
     * @param conditions 资产信息过滤条件
     * @param pageable   分页
     * @return 指定的资产信息列表
     */
    Page<AssetInfo> findAll(AssetInfoDto conditions, Pageable pageable);

    AssetInfo findOne(Long indocno);

    AssetInfo add(AssetInfo assetInfo);

    AssetInfo update(AssetInfo assetInfo);

    void delete(List<AssetInfo> sysCompanys);


}
