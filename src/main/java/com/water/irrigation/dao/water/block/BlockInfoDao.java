package com.water.irrigation.dao.water.block;

import com.water.irrigation.entity.water.block.BlockInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 地块信息Dao
 * @author lichunlei
 *
 */
public interface BlockInfoDao extends JpaRepository<BlockInfo,Long>, JpaSpecificationExecutor<BlockInfo> {

}
