package com.water.irrigation.dao.water.crop;

import com.water.irrigation.entity.water.crops.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 作物信息Dao
 * @author lichunlei
 *
 */
public interface CropDao extends JpaRepository<Crop,Long>, JpaSpecificationExecutor<Crop> {
}
