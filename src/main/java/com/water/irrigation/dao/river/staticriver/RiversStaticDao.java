package com.water.irrigation.dao.river.staticriver;

import com.water.irrigation.entity.river.staticriver.RiversStatic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 河流静态信息Dao
 * @author lichunlei
 *
 */
public interface RiversStaticDao extends JpaRepository<RiversStatic,Long>, JpaSpecificationExecutor<RiversStatic> {

}
