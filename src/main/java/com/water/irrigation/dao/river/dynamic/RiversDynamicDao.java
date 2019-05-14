package com.water.irrigation.dao.river.dynamic;

import com.water.irrigation.entity.river.dynamic.RiversDynamic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 河流动态信息Dao
 * @author lichunlei
 *
 */
public interface RiversDynamicDao extends JpaRepository<RiversDynamic,Long>, JpaSpecificationExecutor<RiversDynamic> {

}
