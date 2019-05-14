package com.water.irrigation.dao.asset;

import com.water.irrigation.entity.asset.AssetInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 资产信息Dao
 * @author lichunlei
 *
 */
public interface AssetInfoDao extends JpaRepository<AssetInfo,Long>, JpaSpecificationExecutor<AssetInfo> {


}
